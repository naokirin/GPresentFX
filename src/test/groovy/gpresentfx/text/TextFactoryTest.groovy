package test.groovy.gpresentfx.text

import spock.lang.Specification
import main.groovy.gpresentfx.GPresentBuilder
import javafx.scene.text.Text
import javafx.scene.paint.Color
import main.groovy.gpresentfx.text.TextFactory
import javafx.scene.paint.Paint


class TextFactoryDefaultAttributeTest extends Specification{
  Text text = TextFactory.newInstance(GPresentBuilder.dsl(), [:])

  def "text"(){
    expect:
      text.text == ""
  }

  def "fontfamily"(){
    expect:
      text.getFont().getFamily() == "Arial"
  }

  def "fontsize"(){
    expect:
      text.getFont().getSize() == 30
  }

  def "textcolor"(){
    expect:
      text.getFill() == Color.BLACK
  }

  def "underline"(){
    expect:
      !text.underline
  }

  def "posx"(){
    expect:
      text.getTranslateX() == 0
  }

  def "posy"(){
    expect:
      text.getTranslateY() == 0
  }

  def "rotate"(){
    expect:
      text.getRotate() == 0
  }
}

class TextFactoryTextAttributeTest extends Specification{
  Text text

  def "text"(Map attribute, String str){
    when:
      text = TextFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      text.getText() == str
    where:
      attribute << [[text:"az"], [text:"09"], [text:"日本語"], [text:""], [text:"\n"]]
      str       << ["az"       , "09"       , "日本語"       , ""       , "\n"]
  }
}

class TextFactoryFontAttributeTest extends Specification{
  Text text

  def "fontfamily"(Map attribute, String fontFamily){
    when:
      text = TextFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      text.getFont().getFamily() == fontFamily
    where:
      attribute << [[fontfamily:"arial"], [fontfamily:"serif"], [fontfamily:""]]
      fontFamily<< ["Arial", "Serif", "System"]
  }

  def "fontsize"(Map attribute, double size){
    when:
      text = TextFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      text.getFont().getSize() >= size - 0.005 && text.getFont().getSize() < size + 0.005
    where:
      attribute << [[fontsize:1], [fontsize:0.1]]
      size      << [1, 0.1]
  }
}

class TextFactoryTextColorAttributeTest extends Specification{
  Text text

  def "textcolor"(Map attribute, Paint color){
    when:
      text = TextFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      text.getFill() == color
    where:
      attribute << [[textcolor:"white"], [textcolor:"#FFFFFF"], [textcolor:"#FFF"], [textcolor:"rgba(255,255,255,1)"]]
      color     << [Color.WHITE        , Color.web("#FFFFFF") , Color.web("#FFF") , Color.rgb(255, 255, 255, 1)]
  }
}

class TextFactoryUnderlineAttributeTest extends Specification{
  Text text

  def "underline"(Map attribute, boolean flag){
    when:
      text = TextFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      text.underline == flag
    where:
      attribute << [[underline:true], [underline:false]]
      flag      << [true, false]
  }
}

class TextFactoryPositionAttributeTest extends Specification{
  Text text

  def "posx"(Map attribute, double pos){
    when:
      text = TextFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      text.getTranslateX() == pos
    where:
      attribute << [[posx:1], [posx:0.1], [posx:-1], [posx:-0.1], [posx:0]]
      pos       << [1       , 0.1       , -1       , -0.1       , 0]
  }

  def "posy"(Map attribute, double pos){
    when:
      text = TextFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      text.getTranslateY() == pos
    where:
      attribute << [[posy:1], [posy:0.1], [posy:-1], [posy:-0.1], [posy:0]]
      pos       << [1       , 0.1       , -1       , -0.1       , 0]
  }
}

class TextFactoryRotateAttributeTest extends Specification{
  Text text

  def "rotate"(Map attribute, double value){
    when:
      text = TextFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      text.getRotate() == value
    where:
      attribute << [[rotate:0], [rotate:1], [rotate:0.1], [rotate:361], [rotate:360.1], [rotate:-1], [rotate:-0.1], [rotate:-360], [rotate:-360.1]]
      value     << [0         , 1         , 0.1         , 361         , 360.1         , -1         , -0.1         , -360         , -360.1]
  }
}