import math.{Color, Vector3D}
import render.{Image, ImageWriter, Scene}

/**
  * DAVID DAVENPORT'UN ASKERLERİYİZ
  *
  * @author Mehmet Can Altuntaş
  * github.com/mehmetcc
  */
object Main extends App {
  val scene = Scene(Vector3D(-2.0, -1.0, -1.0),
                    Vector3D(4.0, 0.0, 0.0),
                    Vector3D(0.0, 2.0, 0.0),
                    Vector3D(0.0, 0.0, 0.0))

  val image = Image(600, 400, scene)
  ImageWriter.write(image)
}
