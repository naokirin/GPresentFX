package main.groovy.gpresentfx

import javafx.scene.input.KeyCode
import javafx.application.Platform
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import javafx.geometry.Pos
import javafx.scene.text.Font
import javafx.geometry.Insets
import javafx.scene.layout.StackPane
import javafx.scene.Scene
import javafx.application.Application
import javafx.stage.Stage
import javafx.event.EventHandler
import javafx.event.Event
import javafx.scene.input.KeyEvent
import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer
import main.groovy.gpresentfx.chart.*
import main.groovy.gpresentfx.image.*
import main.groovy.gpresentfx.layout.*
import main.groovy.gpresentfx.text.*
import javafx.scene.layout.HBox

/**
 * @author naokirin
 * プレゼンテーションアプリのメインクラス
 */
class GroovyPresentFX extends Application{

  private static StackPane topPane = new StackPane()
  private static int page = 0
  private static Slides slides
  private static List slideList
  private static def currentSlide
  // ページカウンター
  private static int pageCounter = 0
  private static def incCounter = { if(pageCounter < page - 1) pageCounter+=1 }
  private static def decCounter = { if(pageCounter > 0) pageCounter-=1}

  public static void main(String[] args){
    Application.launch(GroovyPresentFX, args)
  }

  @Override
  void start(Stage stage) {

    // デフォルトのDSLの設定
    settingDefaultDsl()

    // プレゼンテーションとプラグインスクリプトの読み込み
    new File('./plugin/settingParent').eachFileRecurse{file ->
      if(file.toString() ==~ /.*SettingParent\.groovy/){
        GPresentBuilder.settingParentList << readPlugin(file.toString()) as SettingParentInterface
      }
    }
    new File('./plugin').eachFileRecurse{file ->
      if(file.toString() ==~ /.*Plugin\.groovy/){
        GPresentBuilder.registerDsl(readPlugin(file.toString()) as PluginInterface)
      }
    }

    if(getParameters().raw.isEmpty())
      slides = readPresentation('present.groovy')
    else
      slides = readPresentation(getParameters().raw[0])
    slideList = []
    if(slides == null){
      slideList << new VBox()
      slides = new Slides()
    }
    else{
      slideList = slides.presents
    }

    page = slideList.size()
    currentSlide = slideList[0]
    GroovyPresentFX.slideUpdate()

    stage.setTitle(slides.name)
    stage.setOnCloseRequest(new EventHandler(){
      void handle(Event t) {
        Platform.exit()
        System.exit(0)
      }
    })

    // シーンのセッティング
    Scene scene = new Scene(topPane)
    scene.setOnKeyReleased(new EventHandler(){
      void handle(Event event){
        event = event as KeyEvent
        // F5でDSLスクリプトのリロード
        if(event.getCode() == KeyCode.F5){
          def newSlides
          if(getParameters().raw.isEmpty())
            slides = readPresentation('present.groovy')
          else
            slides = readPresentation(getParameters().raw[0])
          stage.setTitle(slides.name)
          slideList = slides.presents
          page = slideList.size()
          if(page > pageCounter)
            currentSlide = slideList[pageCounter]
          else{
            pageCounter = page - 1
            currentSlide = slideList[pageCounter]
          }
          GroovyPresentFX.slideUpdate()
        }
        else if(event.getCode() == KeyCode.F3){
          currentSlide = {slideList[pageCounter]}.call()
          GroovyPresentFX.slideUpdate()
        }
        // ESCで終了
        else if(event.getCode() == KeyCode.ESCAPE){
          Platform.exit(); System.exit(0)
        }
      }
    })

    scene.setOnKeyPressed(new EventHandler(){
      void handle(Event event){
        event = event as KeyEvent
        // LEFTで1つ前のスライドに戻る
        if(event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.PAGE_DOWN)
          GroovyPresentFX.prev()
        // RIGHTで1つ後のスライドに進む
        else if(event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.PAGE_UP)
          GroovyPresentFX.next()
        // HOMEで最初のスライドに戻る
        else if(event.getCode() == KeyCode.HOME)
          GroovyPresentFX.home()
        // ENDで最後のスライドに進む
        else if(event.getCode() == KeyCode.END)
          GroovyPresentFX.end()
      }
    })

    stage.setScene(scene)
    stage.show()
  }

  // 表示を変更する
  static def slideUpdate ={
    if(currentSlide.metaClass.respondsTo(currentSlide, 'getAlignment'))
      topPane.setAlignment(currentSlide.getAlignment())
    topPane.setStyle(currentSlide.getStyle())
    topPane.children.setAll(currentSlide)
    if(slides.getIsPageCount()){
      def counterPane = new VBox()
      counterPane.setPadding(new Insets(0, 5, 5, 0))
      Text text = new Text((pageCounter+1).toString())
      text.setFont(new Font(30))
      counterPane.children.add(text)
      counterPane.setAlignment(Pos.BOTTOM_RIGHT)
      topPane.children.add(counterPane)
    }
  }

  // スライドの変更
  static def slideChange = {
    currentSlide = slideList[pageCounter]
    GroovyPresentFX.slideUpdate()
  }

  // 1つ先のスライドに進む
  static def next ={
    GroovyPresentFX.incCounter()
    GroovyPresentFX.slideChange()
  }

  // 1つ前のスライドに戻る
  static def prev ={
    GroovyPresentFX.decCounter()
    GroovyPresentFX.slideChange()
  }

  // 最初のスライドに戻る
  static def home ={
    pageCounter = 0
    GroovyPresentFX.slideChange()
  }

  // 最後のスライドに進む
  static def end ={
    pageCounter = page - 1
    GroovyPresentFX.slideChange()
  }

  // プラグインの読み込み
  private def readPlugin = {file ->
    def configuration = new CompilerConfiguration()
    def custom = new ImportCustomizer()
    custom.addImports('main.groovy.gpresentfx.PluginInterface', 'main.groovy.gpresentfx.SettingParentInterface')
    configuration.addCompilationCustomizers(custom)
    String[] paths = ['.']
    def gse = new GroovyScriptEngine(paths)
    gse.setConfig(configuration)
    return gse.loadScriptByName(file.toString()).newInstance()
  }

  // DSLスクリプトの読み込み
  private def readPresentation = {file ->
    def configuration = new CompilerConfiguration()
    def custom = new ImportCustomizer()
    custom.addStaticImport('main.groovy.gpresentfx.GPresentBuilder', 'dsl')
    configuration.addCompilationCustomizers(custom)
    String[] paths = ['.']
    def gse = new GroovyScriptEngine(paths)
    def binding =[input:''] as Binding
    gse.setConfig(configuration)
    return gse.run(file.toString(), binding) as Slides
  }

  // デフォルトのDSLの設定
  private settingDefaultDsl(){
    GPresentBuilder.registerDsl("slides", {pdb, attribute -> return SlidesFactory.newInstance(pdb, attribute)})
    GPresentBuilder.registerDsl("slide", {pdb, attribute -> return SlideFactory.newInstance(pdb, attribute)})
    GPresentBuilder.registerDsl("hbox",  {pdb, attribute -> return BoxLayoutFactory.newInstance(pdb, new HBox(), attribute)})
    GPresentBuilder.registerDsl("vbox", {pdb, attribute -> return BoxLayoutFactory.newInstance(pdb, new VBox(), attribute)})
    GPresentBuilder.registerDsl("stack", {pdb, attribute -> return BoxLayoutFactory.newInstance(pdb, new StackPane(), attribute)})
    GPresentBuilder.registerDsl("border", {pdb, attribute -> return BorderPaneFactory.newInstance(pdb, attribute)})
    GPresentBuilder.registerDsl("top", {pdb, attribute -> return BorderLayoutFactory.newInstance(pdb, GPresentBuilder.borderTopKeyword, attribute)})
    GPresentBuilder.registerDsl("left", {pdb, attribute -> return BorderLayoutFactory.newInstance(pdb, GPresentBuilder.borderLeftKeyword, attribute)})
    GPresentBuilder.registerDsl("center", {pdb, attribute -> return BorderLayoutFactory.newInstance(pdb, GPresentBuilder.borderCenterKeyword, attribute)})
    GPresentBuilder.registerDsl("right", {pdb, attribute -> return BorderLayoutFactory.newInstance(pdb, GPresentBuilder.borderRightKeyword, attribute)})
    GPresentBuilder.registerDsl("bottom", {pdb, attribute -> return BorderLayoutFactory.newInstance(pdb, GPresentBuilder.borderBottomKeyword, attribute)})
    GPresentBuilder.registerDsl("text", {pdb, attribute -> return TextFactory.newInstance(pdb, attribute)})
    GPresentBuilder.registerDsl("image", {pdb, attribute -> return ImageViewFactory.newInstance(pdb, attribute)})
    GPresentBuilder.registerDsl("piechart", {pdb, attribute -> return PieChartFactory.newInstance(pdb, attribute)})
    GPresentBuilder.registerDsl("linechart", {pdb, attribute -> return LineChartFactory.newInstance(pdb, attribute)})
    GPresentBuilder.registerDsl("scatterchart", {pdb, attribute -> return ScatterChartFactory.newInstance(pdb, attribute)})
    GPresentBuilder.registerDsl("barchart", {pdb, attribute -> return BarChartFactory.newInstance(pdb, attribute)})
    GPresentBuilder.registerDsl("areachart", {pdb, attribute -> return AreaChartFactory.newInstance(pdb, attribute)})
    GPresentBuilder.registerDsl("bubblechart", {pdb, attribute -> return BubbleChartFactory.newInstance(pdb, attribute)})
    GPresentBuilder.registerDsl("textarea", {pdb, attribute -> return TextAreaFactory.newInstance(pdb, attribute)})
  }
}

