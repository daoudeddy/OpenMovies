package com.googy.openmovies.base.data.model

//import io.objectbox.annotation.Entity
//import io.objectbox.annotation.Id

//@Entity
abstract class BaseDBEntity(val identifier: String) {
//    @Id
    open var id: Long = 0
}