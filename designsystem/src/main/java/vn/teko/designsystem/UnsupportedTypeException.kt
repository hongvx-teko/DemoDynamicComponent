package vn.teko.designsystem

class UnsupportedTypeException(private val type: Class<*>) :
    RuntimeException("$type is currently unsupported")