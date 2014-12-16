//
// Copyright (c) 2011, chunquedong
// Licensed under the Academic Free License version 3.0
//
// History:
//   2012-07-15  Jed Young  Creation
//

using fgfxGraphics
using fgfxWtk

**
** A display area for a short text string.
**
@Js
class Label : Widget, TextView
{
  override Str text := "Label"
  override Font font := Font(14)

  new make()
  {
  }

  protected override Dimension prefContentSize(Int hintsWidth, Int hintsHeight, Dimension result) {
    return TextView.super.prefContentSize(hintsWidth, hintsHeight, result)
  }
}