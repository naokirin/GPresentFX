package test.groovy.gpresentfx.layout

import spock.lang.Specification
import main.groovy.gpresentfx.layout.SlidesFactory
import main.groovy.gpresentfx.GPresentBuilder

class SlidesFactoryDefaultAttributeTest extends Specification{
  def slides = SlidesFactory.newInstance(GPresentBuilder.dsl(), [:])

  def setup(){}

  def "name"(){
    expect:
      slides.name == ""
  }

  def "pagecounter"(){
    expect:
      !slides.isPageCount
  }

  def "presents"(){
    expect:
      slides.presents.isEmpty()
  }
}

class SlidesFactorySettingAttributeTest extends Specification{
  def slides = SlidesFactory.newInstance(GPresentBuilder.dsl(), [name:"name", pagecounter:true])

  def setup(){
  }

  def "name"(){
    expect:
      slides.name == "name"
  }

  def "pagecounter"(){
    expect:
      slides.isPageCount
  }

  def "presents"(){
    expect:
      slides.presents.isEmpty()
  }
}