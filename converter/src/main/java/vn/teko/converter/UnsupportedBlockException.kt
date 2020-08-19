package vn.teko.converter

class UnsupportedBlockException(private val type: Class<*>) :
    RuntimeException("$type is currently unsupported")