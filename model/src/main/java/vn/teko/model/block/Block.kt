package vn.teko.model.block

interface Block {

    /**
     * identifier of a block
     */
    fun id(): String

    /**
     * all configurations of this block will be listed here, everything about how to layout this block in parent
     * such as padding, margin, size, color, ...
     */
    fun getConfiguration(): Set<Configuration>

}