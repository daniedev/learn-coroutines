# Lambda with a receiver

Lambdas with receivers are important to understand because they are heavily utilized in Kotlin coroutines. 
As you may have noticed, the sequence function mentioned in the [previous concept](../concept2/IntroductionToSequence.kt) also uses a lambda with a receiver, 
which enables us to call the yield function without any qualifiers. Before delving into that, let's briefly review lambdas.

## What is a Lambda?

It is a block of code that can be passed to a function. It allows us to realize Functional Programming, a paradigm where functions are treated as values.

### Syntax

```
{ param1 : Type1, param2 : Type2 -> body }
```

Functions accepting these types of functions are called higher order functions.

### Example for a higher order function:

```kotlin
fun encloseInXMLAttribute(sb : StringBuilder, action : (StringBuilder) -> Unit, attr : String) : String{
    sb.append("<$attr>\n")
    action(sb)
    sb.append("</$attr>")
    return sb.toString()
}
```

```kotlin
//Usage
encloseInXMLAttribute(
    StringBuilder(), 
    { it.append("android:text=\"hello world\"\n") }, 
    "TextView" 
)
```
```xml
<!--Output-->
<TextView>
    android:text="hello world"
</TextView>
```


**For the same Higher order function, if it has lambda as it's last parameter, it can be taken out of parenthesis as shown below.**

```kotlin
//Usage 
encloseInXMLAttribute(
    StringBuilder(),  
    "TextView") {
    it.append("android:text=\"hello world\"\n")
}
```

```kotlin
//Function Declaration
fun encloseInXMLAttribute(sb : StringBuilder, attr : String, action : (StringBuilder) -> Unit) : String{
    sb.append("<$attr>\n")
    action(sb)
    sb.append("</$attr>")
    return sb.toString()
}
```


## Lambda with a Receiver
A lambda with a receiver allows you to call methods of an object in the body of a 
lambda without any qualifiers. It is similar to the typed extension function but this time, 
for function types

Let's refactor the code example and use the extension function syntax.

````kotlin
fun encloseInXMLAttribute(sb: StringBuilder, attr: String, action: StringBuilder.() -> Unit): String {
    sb.append("<$attr>\n")
    sb.action()
    sb.append("/<$attr>")
    return sb.toString()
}
````

here 
````
(StringBuilder) -> Unit 
````
 is refactored to 

````
StringBuilder.() -> Unit
````

and the lambda is invoked as the extension of string builder instance 
received as the 1st parameter.

````kotlin
//Usage
encloseInXMLAttribute(
    StringBuilder(),
    "TextView") {
    append("android:text=\"hello world\"\n")
}

````

The refactored code is an example of lambda with a receiver. It uses extension function
types and simplifies the method invocation at the lambda definition site.

### why do we need to know about lambda with receivers?

Apart from making your code concise, It allows you to write expressive API's that are suited for internal DSL. 
They are good for this purpose because internal DSL's are structured languages and lambda with receivers easily 
provide the ability to structure API's and ability to represent nested structures.

### Common examples of Lambda with a receiver in the kotlin standard library: 

````kotlin
public inline fun <T, R> with(receiver: T, block: T.() -> R): R {
contract {
callsInPlace(block, InvocationKind.EXACTLY_ONCE)
}
return receiver.block()
}

public inline fun <T> T.apply(block: T.() -> Unit): T {
contract {
callsInPlace(block, InvocationKind.EXACTLY_ONCE)
}
block()
return this
}
````

#### with 
It receives a receiver instance and a lambda extension function of the receiver instance type.
It is used to perform operations on a object and return a different object type

#### apply
It is used to operate on the receiver instance and return the same object type.

### Domain Specific Language (DSL)
Unlike a general purpose programming language like kotlin, they are more focussed on 
solving a specific problem and being able to solve it in an efficient manner. Since they focus on 
a particular application domain, the syntax is straight forward compared to other programming languages.
One fine example for a system using DSL would be Gradle.

#### External DSL vs Internal DSL 
External DSL are written in their own syntax and requires effort to integrate to an application 
written in general purpose programming language(eg.kotlin).

Internal DSL on the other hand is written using syntax of a general purpose programming language, but
it still follows rules of a DSL syntax. It's like wrapping your external DSL in your own favorite programming
language.

### Example for an Internal DSL built using Lambda with Receivers

To demonstrate internal DSLs in kotlin using lambda with Receivers,
lets build an android view generator.

[Source Code for Android View Generator](AndroidViewGenerator.kt)

Now, we shall try creating a layout using our custom Android View Generator. Try this out in [Main.kt](../../Main.kt)

````kotlin
val layout = buildAndroidView()
    .addChild("LinearLayout") {
        addAttribute("android:layout_height", "match_parent")
        addAttribute("android:layout_width", "match_parent")
        addAttribute("android:orientation", "vertical")
        addChild("ImageView") {
            addAttribute("android:layout_height", "match_parent")
            addAttribute("android:layout_width", "match_parent")
        }
        addChild("TextView") {
            addAttribute("android:layout_height", "match_parent")
            addAttribute("android:layout_width", "match_parent")
        }
    }
println(layout.getLayout())
````
### Output
````xml
<LinearLayout
 android:layout_height="match_parent"
 android:layout_width="match_parent"
 android:orientation="vertical"
>
<ImageView
 android:layout_height="match_parent"
 android:layout_width="match_parent"
>
</ImageView>
<TextView
 android:layout_height="match_parent"
 android:layout_width="match_parent"
>
</TextView>
</LinearLayout>
````









