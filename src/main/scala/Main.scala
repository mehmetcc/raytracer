import math.{Point, Sphere, Vector3D}
import render.{Color, Image, ImageWriter, Scene}

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

  val origin: Point  = Point(0, 0, -1)
  val radius: Double = 0.5
  val color: Color = Color(1, 0, 0)
  val sphere: Sphere = Sphere(origin, radius, color)

  val image  = Image(400, 200, scene, List(sphere))

  ImageWriter.write(image)
}
