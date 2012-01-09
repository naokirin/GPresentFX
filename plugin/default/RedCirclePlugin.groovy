import javafx.scene.shape.Circle
import main.groovy.gpresentfx.PluginInterface
import javafx.scene.paint.Color

new PluginInterface(){
  String getName() {
    return 'redcircle'
  }

  Closure<Circle> getClosure() {
    return {def obj = new Circle(100); obj.setFill(Color.RED); return obj}
  }
}