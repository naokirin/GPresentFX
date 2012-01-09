package main.groovy.gpresentfx

import javafx.scene.Node

interface PluginInterface {
  // DSL上での名前を指定する
  String getName()

  // 2引数を受け取ってJavaFXのNodeオブジェクトを返すクロージャを返す
  // arg1: main.groovy.gpresentfx.GPresentBuilder
  // arg2: java.util.LinkedHashMap
  Closure<? extends Node> getClosure()
}

interface SettingParentInterface{
  boolean setParent(Object parent, Object child)
}