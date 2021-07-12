package io.worthi.submitQualifier.IF

import io.worthi.submitQualifier.model.qAndA

interface GetQandA_IF {
    fun  getQandA(ques:String,answer:String,pos:String)
}