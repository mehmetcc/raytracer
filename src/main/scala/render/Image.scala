package render

import java.io._

import scala.language.postfixOps

import math.{Color, Point, Ray}

/**
  * DAVID DAVENPORT'UN ASKERLERİYİZ
  *
  * @author Mehmet Can Altuntaş
  * github.com/mehmetcc
  */
case class Scene(lowerLeft: Point,
                 horizontal: Point,
                 vertical: Point,
                 origin: Point)

case class Image(width: Int, height: Int, scene: Scene) {

  def render: List[Color] = calculate.toList

  private def calculate: IndexedSeq[Color] =
    for {
      y <- height-1 to 0 by -1
      x <- 0 until width
    } yield pixel(x, y)

  private def pixel(x: Int, y: Int): Color = {
    val u: Double = x.toDouble / width.toDouble
    val v: Double = y.toDouble / height.toDouble

    val ray = Ray(scene.origin, scene.lowerLeft + scene.horizontal ** u + scene.vertical ** v)
    ray.toColor
  }
}

object ImageWriter {

  def write(image: Image, path: String = "output.ppm") = {
    val file = new File(path)
    using(
      new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
      val lines = image.render
      writer =>
        writer.write("P3\n" + image.width + " " + image.height + "\n255\n")
        /** we are doing io so some side effects are necessary evil :) */
        for (line <- lines) {
          writer.write(line.ir + " " + line.ig + " " + line.ib + "\n")
        }
    }
  }

  // https://stackoverflow.com/questions/28969738/writing-data-generated-in-scala-to-a-text-file
  private def using[T <: Closeable, R](resource: T)(block: T => R): R = {
    try { block(resource) } finally { resource.close() }
  }
}
