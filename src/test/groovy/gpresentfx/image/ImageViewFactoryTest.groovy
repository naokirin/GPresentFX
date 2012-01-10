package test.groovy.gpresentfx.image

import spock.lang.Specification
import javafx.scene.image.ImageView
import main.groovy.gpresentfx.image.ImageViewFactory
import main.groovy.gpresentfx.GPresentBuilder
import javafx.scene.image.Image

class ImageViewFactoryDefaultAttributeTest extends Specification{
  ImageView imageView = ImageViewFactory.newInstance(GPresentBuilder.dsl(), [:])

  def "width"(){
    expect:
      imageView.getFitWidth() == 0
  }

  def "height"(){
    expect:
      imageView.getFitHeight() == 0
  }

  def "preservedratio"(){
    expect:
      !imageView.getImage().preserveRatio
  }

  def "rotate"(){
    expect:
      imageView.getRotate() == 0
  }

  def "posx"(){
    expect:
      imageView.getTranslateX() == 0
  }

  def "posy"(){
    expect:
      imageView.getTranslateY() == 0
  }
}

class ImageViewFactoryFitAttributeTest extends Specification{
  ImageView imageView

  def "width"(Map attribute, double width){
    when:
      imageView = ImageViewFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      imageView.getFitWidth() == width
    where:
      attribute << [[width:1], [width:10], [width:0]]
      width     << [1        , 10        , 0]
  }

  def "height"(Map attribute, double height){
    when:
      imageView = ImageViewFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      imageView.getFitHeight() == height
    where:
      attribute << [[height:1], [height:10], [height:0]]
      height    << [1         , 10         , 0]
  }
}

class ImageViewPreservedRatioAttributeTest extends Specification{
  ImageView imageView

  def "preservedratio"(Map attribute, boolean value){
    when:
      imageView = ImageViewFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      imageView.preserveRatio == value
    where:
      attribute << [[preservedratio:true], [preservedratio:false]]
      value     << [true                 , false]
  }
}

class ImageViewPositionAttributeTest extends Specification{
  ImageView imageView

  def "posx"(Map attribute, double value){
    when:
      imageView = ImageViewFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      imageView.getTranslateX() == value
    where:
      attribute << [[posx:0], [posx:1], [posx:-1], [posx:0.1], [posx:-0.1]]
      value     << [0       , 1       , -1       , 0.1       , -0.1]
  }

  def "posy"(Map attribute, double value){
    when:
      imageView = ImageViewFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      imageView.getTranslateY() == value
    where:
      attribute << [[posy:0], [posy:1], [posy:-1], [posy:0.1], [posy:-0.1]]
      value     << [0       , 1       , -1       , 0.1       , -0.1]
  }

  def ""(Map attribute, double value){
    when:
      imageView = ImageViewFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      imageView.getRotate() == value
    where:
      attribute << [[rotate:0], [rotate:1], [rotate:0.1], [rotate:-1], [rotate:-0.1], [rotate:361], [rotate:-361]]
      value     << [0         , 1         , 0.1         , -1         , -0.1         , 361         , -361]
  }
}
