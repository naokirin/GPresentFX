package test.groovy

import spock.lang.Specification
import main.groovy.gpresentfx.chart.ScatterChartFactory
import main.groovy.gpresentfx.GPresentBuilder
import javafx.scene.chart.ScatterChart
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.geometry.Side
import javafx.scene.chart.CategoryAxis
import javafx.geometry.Pos

class ScatterChartFactoryDefaultAttributeTest extends Specification{
  ScatterChart scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), [:])

  def "data"(){
    expect:
      scatterChart.getData().isEmpty()
  }

  def "title"(){
    expect:
      scatterChart.getTitle() == null
  }

  def "xtitle"(){
    expect:
      scatterChart.getXAxis().getLabel() == null
  }

  def "ytitle"(){
    expect:
      scatterChart.getYAxis().getLabel() == null
  }

  def "xcategory"(){
    expect:
      scatterChart.getXAxis().class == NumberAxis
  }

  def "ycategory"(){
    expect:
      scatterChart.getYAxis().class == NumberAxis
  }

  def "xautorange"(){
    expect:
      scatterChart.getXAxis().isAutoRanging()
  }

  def "yautorange"(){
    expect:
      scatterChart.getYAxis().isAutoRanging()
  }

  def "rotate"(){
    expect:
      scatterChart.getRotate() == 0
  }

  def "posx"(){
    expect:
      scatterChart.getTranslateX() == 0
  }

  def "posy"(){
    expect:
      scatterChart.getTranslateY() == 0
  }
}

class ScatterChartFactoryOneSeriesDataAttributeTest extends Specification{
  ScatterChart scatterChart

  def "data of one point"(){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), [data:[a:[0:0]]])
    then:
      scatterChart.getData().size() == 1
      scatterChart.getData().get(0).class == XYChart.Series
      scatterChart.getData().get(0).name == "a"
      scatterChart.getData().get(0).data.get(0).class == XYChart.Data
      scatterChart.getData().get(0).data.size() == 1
      scatterChart.getData().get(0).data.get(0).XValue == 0
      scatterChart.getData().get(0).data.get(0).YValue == 0
  }

  def "data of two point"(){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), [data:[a:[0:1, 2:3]]])
    then:
      scatterChart.getData().size() == 1
      scatterChart.getData().get(0).class == XYChart.Series
      scatterChart.getData().get(0).name == "a"
      scatterChart.getData().get(0).data.size() == 2
      scatterChart.getData().get(0).data.get(0).class == XYChart.Data
      scatterChart.getData().get(0).data.get(0).XValue == 0
      scatterChart.getData().get(0).data.get(0).YValue == 1
      scatterChart.getData().get(0).data.get(1).class == XYChart.Data
      scatterChart.getData().get(0).data.get(1).XValue == 2
      scatterChart.getData().get(0).data.get(1).YValue == 3
  }
}

class ScatterChartFactoryTwoSeriesDataAttributeTest extends Specification{
  ScatterChart scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), [data:["a":[0:0], "b":[1:2]]])

  def "data"(){
    expect:
      scatterChart.getData().size() == 2
      scatterChart.getData().get(0).class == XYChart.Series
      scatterChart.getData().get(0).name == "a"
      scatterChart.getData().get(0).data.size() == 1
      scatterChart.getData().get(0).data.get(0).class == XYChart.Data
      scatterChart.getData().get(0).data.get(0).XValue == 0
      scatterChart.getData().get(0).data.get(0).YValue == 0

      scatterChart.getData().get(1).class == XYChart.Series
      scatterChart.getData().get(1).name == "b"
      scatterChart.getData().get(1).data.size() == 1
      scatterChart.getData().get(1).data.get(0).class == XYChart.Data
      scatterChart.getData().get(1).data.get(0).XValue == 1
      scatterChart.getData().get(1).data.get(0).YValue == 2
  }
}

class ScatterChartFactoryTitleSideAttributeTest extends Specification{
  ScatterChart scatterChart

  def "title"(Map attribute, String title){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      scatterChart.getTitle() == title
    where:
      attribute << [[title:""], [title:" "], [title:"azAZ"], [title:"09"], [title:"日本語"]]
      title     << [""        , " "        , "azAZ"        , "09"        , "日本語"]
  }

  def "titleside"(Map attribute, Side side){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      scatterChart.getTitleSide() == side
    where:
      attribute << [[titleside:"top"], [titleside:"TOP"], [titleside:"Top"], [titleside:"bottom"], [titleside:"right"], [titleside:"left"]]
      side      << [Side.TOP         , Side.TOP          , Side.TOP         , Side.BOTTOM          , Side.RIGHT         , Side.LEFT]
  }
}

class ScatterChartFactoryAxisTitleAttributeTest extends Specification{
  ScatterChart scatterChart

  def "xtitle"(Map attribute, String title){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      scatterChart.getXAxis().getLabel() == title
    where:
      attribute << [[xtitle:""], [xtitle:" "], [xtitle:"azAZ"], [xtitle:"09"], [xtitle:"日本語"]]
      title     << [""        , " "        , "azAZ"        , "09"        , "日本語"]
  }

def "ytitle"(Map attribute, String title){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      scatterChart.getYAxis().getLabel() == title
    where:
      attribute << [[ytitle:""], [ytitle:" "], [ytitle:"azAZ"], [ytitle:"09"], [ytitle:"日本語"]]
      title     << [""        , " "        , "azAZ"        , "09"        , "日本語"]
  }
}

class ScatterChartFactoryCategoryAttributeTest extends Specification{
  ScatterChart scatterChart

  def "xcategory is true"(){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), [xcategory:true])
    then:
      scatterChart.getXAxis().class == CategoryAxis
  }

  def "ycategory is true"(){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), [ycategory:true])
    then:
      scatterChart.getYAxis().class == CategoryAxis
  }

  def "xcategory is false"(){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), [xcategory:false])
    then:
      scatterChart.getXAxis().class == NumberAxis
  }

  def "ycategory is false"(){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), [ycategory:false])
    then:
      scatterChart.getYAxis().class == NumberAxis
  }
}

class ScatterChartFactoryAutoRangeAttributeTest extends Specification{
  ScatterChart scatterChart

  def "xautorange is false"(){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), [xautorange:false])
    then:
      !scatterChart.getXAxis().isAutoRanging()
  }

  def "yautorange is false"(){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), [yautorange:false])
    then:
      !scatterChart.getYAxis().isAutoRanging()
  }

  def "xautorange is true"(){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), [xautorange:true])
    then:
      scatterChart.getXAxis().isAutoRanging()
  }

  def "yautorange is true"(){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), [yautorange:true])
    then:
      scatterChart.getYAxis().isAutoRanging()
  }
}

class ScatterChartFactoryRangeAttributeTest extends Specification{
  ScatterChart scatterChart

  def "xrange"(Map attribute, double lower, double upper){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.newInstance(), attribute)
    then:
      scatterChart.getXAxis().getLowerBound() == lower
      scatterChart.getXAxis().getUpperBound() == upper
    where:
      attribute << [[xrange:[0, 1]], [xrange:[0.0, 0.1]], [xrange:[-0.2, -0.1]]]
      lower     << [0              , 0.0                , -0.2]
      upper     << [1              , 0.1                , -0.1]
  }
}

class ScatterChartFactoryUnitAttributeTest extends Specification{
  ScatterChart scatterChart

  def "xunit"(Map attribute, double value){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      scatterChart.getXAxis().getTickUnit() == value
    where:
      attribute << [[xunit:1, xautorange:false], [xunit:0.1, xautorange:false]]
      value     << [1        , 0.1]
  }

  def "yunit"(Map attribute, double value){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      scatterChart.getYAxis().getTickUnit() == value
    where:
      attribute << [[yunit:1, yautorange:false], [yunit:0.1, yautorange:false]]
      value     << [1        , 0.1]
  }
}

class ScatterChartFactoryConfigurationAttributeTest extends Specification{
  ScatterChart scatterChart

  def "rotate of plus value"(Map attribute, double value){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      scatterChart.getRotate() == value
    where:
      attribute << [[rotate:1], [rotate:0.1], [rotate:10.0f]]
      value     << [1         , 0.1         , 10.0]
  }

  def "rotate of minus value"(Map attribute, double value){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.newInstance(), attribute)
    then:
      scatterChart.getRotate() == value
    where:
      attribute << [[rotate:-1], [rotate: -0.1], [rotate:-10.0f]]
      value     << [-1         , -0.1          , -10.0f]
  }

  def "rotate over 360"(Map attribute, double value){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.newInstance(), attribute)
    then:
      scatterChart.getRotate() == value
    where:
      attribute << [[rotate:361], [rotate:360.1], [rotate:360.0f], [rotate:-361], [rotate:-360.1]]
      value     << [361         , 360.1         , 360.0f         , -361         , -360.1]
  }

  def "posx"(Map attribute, double value){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      scatterChart.getTranslateX() == value
    where:
      attribute << [[posx:0], [posx:0.1], [posx:1], [posx:0.1f], [posx:-1], [posx:-0.1]]
      value     << [0       , 0.1       , 1       , 0.1f       , -1       , -0.1]
  }

  def "posy"(Map attribute, double value){
    when:
      scatterChart = ScatterChartFactory.newInstance(GPresentBuilder.dsl(), attribute)
    then:
      scatterChart.getTranslateY() == value
    where:
      attribute << [[posy:0], [posy:0.1], [posy:1], [posy:0.1f], [posy:-1], [posy:-0.1]]
      value     << [0       , 0.1       , 1       , 0.1f       , -1       , -0.1]
  }
}