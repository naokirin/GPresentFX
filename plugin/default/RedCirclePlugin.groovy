import javafx.scene.shape.Circle
import javafx.scene.paint.Color

class RedCirclePlugin implements PluginInterface{
  String getName(){
    return 'redcircle'
  }

  Closure<Circle> getClosure(){
    return {def obj = new Circle(100); obj.setFill(Color.RED); return obj}
  }
}
