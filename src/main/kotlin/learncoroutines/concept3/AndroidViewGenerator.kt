package learncoroutines.concept3

/**
 This code aims to demonstrate on building internal DSLs in kotlin using lambda with Receivers.
 To learn more about lambda with Receivers, read the LambdaWithaReceiver.md file in the same package [learncoroutines.concept3].
 Read the contents of the file in GitHub or using IntelliJ/AndroidStudio IDE since it is a markdown(.md) file.
 */

fun buildAndroidView() = XMLContainer()

open class XMLContainer {

    private val parentTag = StringBuilder()

    fun addChild(tagName: String, build: XMLChildContainer.() -> Unit): XMLContainer {
        parentTag.append("<$tagName>\n")
        val child = XMLChildContainer()
        child.build()
        parentTag.append(child.getAttributes())
        parentTag.append(">\n")
        parentTag.append(child.getLayout())
        parentTag.append("</$tagName>\n")
        return this
    }

    fun getLayout() = parentTag.toString()
}

class XMLChildContainer : XMLContainer() {

    private val tagContents = StringBuilder()

    fun addAttribute(attributeName: String, attributeValue: String) {
        tagContents.append("$attributeName=\"$attributeValue\"\n")
    }

    fun getAttributes() = tagContents.toString()
}