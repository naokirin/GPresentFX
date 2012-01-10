package test.groovy

import spock.lang.Specification
import main.groovy.gpresentfx.chart.PieChartFactory
import main.groovy.gpresentfx.GPresentBuilder
import javafx.scene.chart.PieChart
import javafx.geometry.Side
import javafx.scene.chart.PieChart.Data

class PieChartFactoryDefaultAttributeTest extends Specification{
  PieChart pieChart = PieChartFactory.newInstance(GPresentBuilder.dsl(), [:])

  def "data"(){
    expect:
      pieChart.getData().isEmpty()
  }

  def "title"(){
    expect:
      pieChart.getTitle() == null
  }

  def "startangle"(){
    expect:
      pieChart.getStartAngle() == 0
  }

  def "labelvisible"(){
    expect:
      pieChart.getLabelsVisible()
  }

  def "rotate"(){
    expect:
      pieChart.getRotate() == 0
  }

  def "posx"(){
    expect:
      pieChart.getTranslateX() == 0
  }

  def "posy"(){
    expect:
      pieChart.getTranslateY() == 0
  }
}

class PieChartFactoryOneSeriesDataAttributeTest extends Specification{
  PieChart pieChart

  def "data of one label"(){
    when:
      pieChart = PieChartFactory.newInstance(GPresentBuilder.dsl(), [data:[a:1]])
    then:
      pieChart.getData().size() == 1
      pieChart.getData().get(0).class == Data
      pieChart.getData().get(0).name == "a"
      pieChart.getData().get(0).pieValue == 1
  }

  def "data of two label"(){
    when:
      pieChart = PieChartFactory.newInstance(GPresentBuilder.dsl(), [data:[a:1, b:2]])
    then:
      pieChart.getData().size() == 2
      pieChart.getData().get(0).class == Data
      pieChart.getData().get(0).name == "a"
      pieChart.getData().get(0).pieValue == 1
      pieChart.getData().get(1).class == Data
      pieChart.getData().get(1).name == "b"
      pieChart.getData().get(1).pieValue == 2
  }

  def "data of empty"(){
    when:
       PieChart pieChart = PieChartFactory.newInstance(GPresentBuilder.dsl(), [data:[]])
    then:
      pieChart.getData().isEmpty()
  }
}

class PieChartFactoryTitleSideAttributeTest extends Specification{
  PieChart pieChart

  def "title"(Map attribute, String title){
    when:
      pieChart = PieChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      pieChart.getTitle() == title
    where:
      attribute << [[title:""], [title:" "], [title:"azAZ"], [title:"09"], [title:"日本語"]]
      title     << [""        , " "        , "azAZ"        , "09"        , "日本語"]
  }

  def "titleside"(Map attribute, Side side){
    when:
      pieChart = PieChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      pieChart.getTitleSide() == side
    where:
      attribute << [[titleside:"top"], [titleside:"TOP"], [titleside:"Top"], [titleside:"bottom"], [titleside:"right"], [titleside:"left"]]
      side      << [Side.TOP         , Side.TOP          , Side.TOP         , Side.BOTTOM          , Side.RIGHT         , Side.LEFT]
  }
}

class PieChartFactoryStartAngleAttributeTest extends Specification{
  PieChart pieChart

  def "startangle"(Map attribute, double angle){
    when:
      pieChart = PieChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      pieChart.getStartAngle() == angle
    where:
      attribute << [[startangle:0], [startangle:0.1], [startangle:1], [startangle:-1], [startangle:-0.1], [startangle:361], [startangle:-361]]
      angle     << [0             , 0.1             , 1             , -1             , -0.1             , 361             , -361]
  }
}

class PieChartFactoryLabelVisibleAttributeTest extends Specification{
  PieChart pieChart

  def "labelvisible"(Map attribute, boolean value){
    when:
      pieChart = PieChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      pieChart.getLabelsVisible() == value
    where:
      attribute << [[labelvisible:false], [labelvisible:true]]
      value     << [false, true]
  }
}

class PieChartFactoryConfigurationAttributeTest extends Specification{
  PieChart pieChart

  def "rotate of plus value"(Map attribute, double value){
    when:
      pieChart = PieChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      pieChart.getRotate() == value
    where:
      attribute << [[rotate:1], [rotate:0.1], [rotate:10.0f]]
      value     << [1         , 0.1         , 10.0]
  }

  def "rotate of minus value"(Map attribute, double value){
    when:
      pieChart = PieChartFactory.newInstance(GPresentBuilder.newInstance(), attribute)
    then:
      pieChart.getRotate() == value
    where:
      attribute << [[rotate:-1], [rotate: -0.1], [rotate:-10.0f]]
      value     << [-1         , -0.1          , -10.0f]
  }

  def "rotate over 360"(Map attribute, double value){
    when:
      pieChart = PieChartFactory.newInstance(GPresentBuilder.newInstance(), attribute)
    then:
      pieChart.getRotate() == value
    where:
      attribute << [[rotate:361], [rotate:360.1], [rotate:360.0f], [rotate:-361], [rotate:-360.1]]
      value     << [361         , 360.1         , 360.0f         , -361         , -360.1]
  }

  def "posx"(Map attribute, double value){
    when:
      pieChart = PieChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      pieChart.getTranslateX() == value
    where:
      attribute << [[posx:0], [posx:0.1], [posx:1], [posx:0.1f], [posx:-1], [posx:-0.1]]
      value     << [0       , 0.1       , 1       , 0.1f       , -1       , -0.1]
  }

  def "posy"(Map attribute, double value){
    when:
      pieChart = PieChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      pieChart.getTranslateY() == value
    where:
      attribute << [[posy:0], [posy:0.1], [posy:1], [posy:0.1f], [posy:-1], [posy:-0.1]]
      value     << [0       , 0.1       , 1       , 0.1f       , -1       , -0.1]
  }
}