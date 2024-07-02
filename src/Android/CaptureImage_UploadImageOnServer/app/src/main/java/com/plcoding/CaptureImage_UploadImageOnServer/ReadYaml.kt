package com.plcoding.CaptureImage_UploadImageOnServer

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.yaml.snakeyaml.Yaml
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths

class ReadYaml {
    fun readFileAsLinesUsingBufferedReader(fileName: String): List<String>
            = File(fileName).bufferedReader().readLines()
    fun readFileAsTextUsingInputStream(fileName: String)
            = File(fileName).inputStream().readBytes().toString(Charsets.UTF_8)


}