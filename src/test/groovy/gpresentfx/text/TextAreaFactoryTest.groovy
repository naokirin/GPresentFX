package test.groovy.gpresentfx.text

import spock.lang.Specification
import javafx.scene.control.TextArea
import main.groovy.gpresentfx.text.TextAreaFactory
import main.groovy.gpresentfx.GPresentBuilder
import javafx.geometry.Pos

class TextAreaFactoryDefaultAttributeTest extends Specification{
  TextArea textArea = TextAreaFactory.newInstance(GPresentBuilder.dsl(), [:])

  def "text"(){
    expect:
      textArea.getText() == ""
  }

  def "editable"(){
    expect:
      textArea.editable
  }

  def "posx"(){
    expect:
      textArea.getTranslateX() == 0
  }

  def "posy"(){
    expect:
      textArea.getTranslateY() == 0
  }

  def "rotate"(){
    expect:
      textArea.getRotate() == 0
  }
}

class TextAreaTextAttributeTest extends Specification{
  TextArea textArea

  def "text"(Map attribute, String value){
    when:
      textArea = TextAreaFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      textArea.getText() == value
    where:
      attribute << [[text:"a"], [text:"azAZ"], [text:"\n"], [text:""], [text:"日本語"]]
      value     << ["a"       , "azAZ"       , "\n"       , ""       , "日本語"]
  }
}

class TextAreaEditableAttributeTest extends Specification{
  TextArea textArea

  def "editable"(Map attribute, boolean value){
    when:
      textArea = TextAreaFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      textArea.editable == value
    where:
      attribute << [[editable:true], [editable:false]]
      value     << [true           , false]
  }
}

class TextAreaColumnAndRowAttributeTest extends Specification{
  TextArea textArea

  def "column"(Map attribute, int value){
    when:
      textArea = TextAreaFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      textArea.getPrefColumnCount() == value
    where:
      attribute << [[column:1], [column:10], [column:0]]
      value     << [1         , 10         , 0]
  }

  def "row"(Map attribute, int value){
    when:
      textArea = TextAreaFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      textArea.getPrefRowCount() == value
    where:
      attribute << [[row:1], [row:10], [row:0]]
      value     << [1      , 10      , 0]
  }
}

class TextAreaPositionAttributeTest extends Specification{
  TextArea textArea

  def "posx"(Map attribute, double pos){
    when:
      textArea = TextAreaFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      textArea.getTranslateX() == pos
    where:
      attribute << [[posx:0], [posx:1], [posx:-1], [posx:0.1], [posx:-0.1]]
      pos       << [0       , 1       , -1       , 0.1       , -0.1]
  }

  def "posy"(Map attribute, double pos){
    when:
      textArea = TextAreaFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      textArea.getTranslateY() == pos
    where:
      attribute << [[posy:0], [posy:1], [posy:-1], [posy:0.1], [posy:-0.1]]
      pos       << [0       , 1       , -1       , 0.1       , -0.1]
 }

  def "rotate"(Map attribute, double angle){
    when:
      textArea = TextAreaFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      textArea.getRotate() == angle
    where:
      attribute << [[rotate:0], [rotate:1], [rotate:-1], [rotate:0.1], [rotate:-0.1], [rotate:361], [rotate:-361]]
      angle     << [0         , 1         , -1         , 0.1         , -0.1         , 361         , -361]
  }
}