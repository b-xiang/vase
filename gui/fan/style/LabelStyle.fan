//
// Copyright (c) 2011, chunquedong
// Licensed under the Academic Free License version 3.0
//
// History:
//   2012-07-15  Jed Young  Creation
//

using vaseGraphics
using vaseWindow

@Js
class LabelStyle : WidgetStyle
{
  override Void doPaint(Widget widget, Graphics g)
  {
    Label lab := widget
    drawText(widget, g, lab.text, Align.begin)
  }
}

@Js
class ToastStyle : WidgetStyle {

  new make() {
    background = Color(0x434343)
    fontColor = Color.white
  }

  override Void doPaint(Widget widget, Graphics g)
  {
    Toast lab := widget

    width := widget.width
    height := widget.height
    g.brush = background
    Int arc := height/2
    g.fillRoundRect(0, 0, width, height, arc, arc)

    drawText(widget, g, lab.text, Align.center)
  }
}