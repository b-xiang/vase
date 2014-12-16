//
// Copyright (c) 2014, chunquedong
// Licensed under the Academic Free License version 3.0
//
// History:
//   2014-11-14  Jed Young  Creation
//

using fgfxGraphics
using fgfxWtk
using fgfxMath


@Js
class ShadowEffect : BlurEffect {
  private Int blurRadius := 1
  private Int xOffset := 2
  private Int yOffset := 2

  new make() {
    gray = true
  }

  override Graphics prepare(Widget widget, Graphics g) {

    Size size := Size(widget.width+2, widget.height+2)
    bufImage = tryMakeImage(bufImage, size)
    originalGraphics = g
    imageGraphics := bufImage.graphics
    imageGraphics.brush = Color.makeArgb(0, 0, 0, 0)
    imageGraphics.clearRect(0, 0, widget.width, widget.height)
    imageGraphics.brush = Color.makeArgb(255, 255, 255, 255)
    return imageGraphics
  }

  override Void end(|Graphics| paint) {
    filter(bufImage)
    originalGraphics.drawImage(bufImage, xOffset, yOffset)

    paint(originalGraphics)
  }
}