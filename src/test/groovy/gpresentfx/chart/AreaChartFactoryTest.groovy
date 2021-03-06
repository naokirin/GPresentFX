package test.groovy.gpresentfx.chart

import spock.lang.Specification
import main.groovy.gpresentfx.chart.AreaChartFactory
import main.groovy.gpresentfx.GPresentBuilder
import javafx.scene.chart.AreaChart
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.geometry.Side
import javafx.scene.chart.CategoryAxis

class AreaChartFactoryDefaultAttributeTest extends Specification{
  AreaChart areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), [:])

  def "data"(){
    expect:
      areaChart.getData().isEmpty()
  }

  def "title"(){
    expect:
      areaChart.getTitle() == null
  }

  def "xtitle"(){
    expect:
      areaChart.getXAxis().getLabel() == null
  }

  def "ytitle"(){
    expect:
      areaChart.getYAxis().getLabel() == null
  }

  def "xcategory"(){
    expect:
      areaChart.getXAxis().class == NumberAxis
  }

  def "ycategory"(){
    expect:
      areaChart.getYAxis().class == NumberAxis
  }

  def "xautorange"(){
    expect:
      areaChart.getXAxis().isAutoRanging()
  }

  def "yautorange"(){
    expect:
      areaChart.getYAxis().isAutoRanging()
  }

  def "rotate"(){
    expect:
      areaChart.getRotate() == 0
  }

  def "posx"(){
    expect:
      areaChart.getTranslateX() == 0
  }

  def "posy"(){
    expect:
      areaChart.getTranslateY() == 0
  }
}

class AreaChartFactoryOneSeriesDataAttributeTest extends Specification{
  AreaChart areaChart

  def "data of one point"(){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), [data:[a:[0:0]]])
    then:
      areaChart.getData().size() == 1
      areaChart.getData().get(0).class == XYChart.Series
      areaChart.getData().get(0).name == "a"
      areaChart.getData().get(0).data.get(0).class == XYChart.Data
      areaChart.getData().get(0).data.size() == 1
      areaChart.getData().get(0).data.get(0).XValue == 0
      areaChart.getData().get(0).data.get(0).YValue == 0
  }

  def "data of two point"(){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), [data:[a:[0:1, 2:3]]])
    then:
      areaChart.getData().size() == 1
      areaChart.getData().get(0).class == XYChart.Series
      areaChart.getData().get(0).name == "a"
      areaChart.getData().get(0).data.size() == 2
      areaChart.getData().get(0).data.get(0).class == XYChart.Data
      areaChart.getData().get(0).data.get(0).XValue == 0
      areaChart.getData().get(0).data.get(0).YValue == 1
      areaChart.getData().get(0).data.get(1).class == XYChart.Data
      areaChart.getData().get(0).data.get(1).XValue == 2
      areaChart.getData().get(0).data.get(1).YValue == 3
  }

  def "data of empty"(){
    when:
       AreaChart areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), [data:["a":[:]]])
    then:
      areaChart.getData().size() == 1
      areaChart.getData().get(0).class == XYChart.Series
      areaChart.getData().get(0).name == "a"
      areaChart.getData().get(0).data.size() == 0
  }
}

class AreaChartFactoryTwoSeriesDataAttributeTest extends Specification{
  AreaChart areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), [data:["a":[0:0], "b":[1:2]]])

  def "data"(){
    expect:
      areaChart.getData().size() == 2
      areaChart.getData().get(0).class == XYChart.Series
      areaChart.getData().get(0).name == "a"
      areaChart.getData().get(0).data.size() == 1
      areaChart.getData().get(0).data.get(0).class == XYChart.Data
      areaChart.getData().get(0).data.get(0).XValue == 0
      areaChart.getData().get(0).data.get(0).YValue == 0

      areaChart.getData().get(1).class == XYChart.Series
      areaChart.getData().get(1).name == "b"
      areaChart.getData().get(1).data.size() == 1
      areaChart.getData().get(1).data.get(0).class == XYChart.Data
      areaChart.getData().get(1).data.get(0).XValue == 1
      areaChart.getData().get(1).data.get(0).YValue == 2
  }
}

class AreaChartFactoryTitleSideAttributeTest extends Specification{
  AreaChart areaChart

  def "title"(Map attribute, String title){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      areaChart.getTitle() == title
    where:
      attribute       | title
      [title:""]      | ""
      [title:" "]     | " "
      [title:"azAZ"]  | "azAZ"
      [title:"09"]    | "09"
      [title:"日本語"]| "日本語"
  }

  def "titleside"(Map attribute, Side side){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      areaChart.getTitleSide() == side
    where:
      attribute              | side
      [titleside:"top"]     | Side.TOP
      [titleside:"TOP"]     | Side.TOP
      [titleside:"Top"]     | Side.TOP
      [titleside:"bottom"]  | Side.BOTTOM
      [titleside:"right"]   | Side.RIGHT
      [titleside:"left"]    | Side.LEFT
  }
}

class AreaChartFactoryAxisTitleAttributeTest extends Specification{
  AreaChart areaChart

  def "xtitle"(Map attribute, String title){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      areaChart.getXAxis().getLabel() == title
    where:
      attribute        | title
      [xtitle:""]      | ""
      [xtitle:" "]     | " "
      [xtitle:"azAZ"]  | "azAZ"
      [xtitle:"09"]    | "09"
      [xtitle:"日本語"]| "日本語"
  }

def "ytitle"(Map attribute, String title){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      areaChart.getYAxis().getLabel() == title
    where:
      attribute         | title
      [ytitle:""]       | ""
      [ytitle:" "]      | " "
      [ytitle:"azAZ"]   | "azAZ"
      [ytitle:"09"]     | "09"
      [ytitle:"日本語"] | "日本語"

  }
}

class AreaChartFactoryCategoryAttributeTest extends Specification{
  AreaChart areaChart

  def "xcategory is true"(){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), [xcategory:true])
    then:
      areaChart.getXAxis().class == CategoryAxis
  }

  def "ycategory is true"(){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), [ycategory:true])
    then:
      areaChart.getYAxis().class == CategoryAxis
  }

  def "xcategory is false"(){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), [xcategory:false])
    then:
      areaChart.getXAxis().class == NumberAxis
  }

  def "ycategory is false"(){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), [ycategory:false])
    then:
      areaChart.getYAxis().class == NumberAxis
  }
}

class AreaChartFactoryAutoRangeAttributeTest extends Specification{
  AreaChart areaChart

  def "xautorange is false"(){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), [xautorange:false])
    then:
      !areaChart.getXAxis().isAutoRanging()
  }

  def "yautorange is false"(){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), [yautorange:false])
    then:
      !areaChart.getYAxis().isAutoRanging()
  }

  def "xautorange is true"(){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), [xautorange:true])
    then:
      areaChart.getXAxis().isAutoRanging()
  }

  def "yautorange is true"(){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), [yautorange:true])
    then:
      areaChart.getYAxis().isAutoRanging()
  }
}

class AreaChartFactoryRangeAttributeTest extends Specification{
  AreaChart areaChart

  def "xrange"(Map attribute, double lower, double upper){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.newInstance(), attribute)
    then:
      areaChart.getXAxis().getLowerBound() == lower
      areaChart.getXAxis().getUpperBound() == upper
    where:
      attribute             | lower | upper
      [xrange:[0, 1]]       | 0     |1
      [xrange:[0.0, 0.1]]   | 0.0   |0.1
      [xrange:[-0.2, -0.1]] | -0.2  |-0.1
  }
}

class AreaChartFactoryUnitAttributeTest extends Specification{
  AreaChart areaChart

  def "xunit"(Map attribute, double value){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      areaChart.getXAxis().getTickUnit() == value
    where:
      attribute                      | value
      [xunit:1, xautorange:false]   | 1
      [xunit:0.1, xautorange:false] | 0.1
  }

  def "yunit"(Map attribute, double value){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      areaChart.getYAxis().getTickUnit() == value
    where:
      attribute                     | value
     [yunit:1, yautorange:false]   | 1
     [yunit:0.1, yautorange:false] | 0.1
  }
}

class AreaChartFactoryConfigurationAttributeTest extends Specification{
  AreaChart areaChart

  def "rotate of plus value"(Map attribute, double value){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      areaChart.getRotate() == value
    where:
      attribute       | value
      [rotate:1]      | 1
      [rotate:0.1]    | 0.1
      [rotate:10.0f]  | 10.0
  }

  def "rotate of minus value"(Map attribute, double value){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.newInstance(), attribute)
    then:
      areaChart.getRotate() == value
    where:
      attribute       | value
      [rotate:-1]     | -1
      [rotate: -0.1]  | -0.1
      [rotate:-10.0f] | -10.0f
  }

  def "rotate over 360"(Map attribute, double value){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.newInstance(), attribute)
    then:
      areaChart.getRotate() == value
    where:
      attribute       | value
      [rotate:361]    | 361
      [rotate:360.1]  | 360.1
      [rotate:360.0f] | 360.0
      [rotate:-361]   | -361
      [rotate:-360.1] | -360.1
  }

  def "posx"(Map attribute, double value){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      areaChart.getTranslateX() == value
    where:
      attribute   | value
      [posx:0]    | 0
      [posx:0.1]  | 0.1
      [posx:1]    | 1
      [posx:-1]   | -1
      [posx:-0.1] | -0.1
  }

  def "posy"(Map attribute, double value){
    when:
      areaChart = AreaChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      areaChart.getTranslateY() == value
    where:
      attribute  | value
      [posy:0]   | 0
      [posy:0.1] | 0.1
      [posy:1]   | 1
      [posy:-1]  | -1
      [posy:-0.1]| -0.1
  }
}