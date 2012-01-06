package main.groovy.gpresentfx.layout

import javafx.scene.layout.Pane
import javafx.geometry.Insets
import main.groovy.gpresentfx.GPresentBuilder
import main.groovy.gpresentfx.AbstractRegionFactory

abstract class AbstractLayoutFactory extends AbstractRegionFactory {
  static final String backgroundKeyword = 'background'
  static final String paddingKeyword = 'padding'

  protected static void setBackground(Pane pane, Map attribute, GPresentBuilder pdb){
    if(attribute[backgroundKeyword] != null)
      pane.setStyle("-fx-background-color: ${attribute[backgroundKeyword].toString()};")
  }

  protected static void setPadding(Pane pane, Map attribute, GPresentBuilder pdb, boolean isDefault){
    if(attribute[paddingKeyword] != null){
      if(attribute[paddingKeyword] instanceof List){
        def padding = attribute[paddingKeyword] as List
        if(padding.size() == 4)
          pane.setPadding(new Insets((double)padding[0], (double)padding[1], (double)padding[2], (double)padding[3]))
        if(padding.size() == 2)
          pane.setPadding(new Insets((double)padding[0], (double)padding[1], (double)padding[0], (double)padding[1]))
      }
      else{
        def padding = (double)attribute[paddingKeyword]
        pane.setPadding(new Insets(padding, padding, padding, padding))
      }
    }
    else{
      if(isDefault)
        pane.setPadding(pdb.defaultPadding)
    }
  }
}