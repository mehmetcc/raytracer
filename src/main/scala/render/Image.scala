package render

import java.io._

import math.{Point, Ray, Shape, Sphere}

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

class Image(val width: Int,
            val height: Int,
            val scene: Scene,
            val shapes: List[Sphere]) {

  def render: List[Color] = calculate.toList

  /**
    * There used to be a whole lot of complex map operations instead of this for loop
    * Then I watched Odersky's (ie. God) lecture about for loops, and he was all like,
    * Mehmet, it is okay to use loops now and then, and look how beautiful this code
    * turn out to be... People who don't write Scala are losers
   **/
  private def calculate: IndexedSeq[Color] =
    for {
      y <- height - 1 to 0 by -1
      x <- 0 until width
    } yield pixel(x, y)

  /** TODO i think this can be parallelized real good */
  private def pixel(x: Int, y: Int): Color = {
    val u: Double = x.toDouble / width.toDouble
    val v: Double = y.toDouble / height.toDouble

    val ray = Ray(scene.origin,
                  scene.lowerLeft + scene.horizontal ** u + scene.vertical ** v)
    // TODO this should be for all shapes
    // TODO this as of now is purely testing
    val current: Sphere = shapes(0)
    current.shade(ray) match {
      case Some(intersection) => intersection
      case None => ray.toColor
    }
  }

}

object Image {
  def apply(width: Int, height: Int, scene: Scene, shapes: List[Sphere] = List.empty): Image =
    new Image(width: Int, height: Int, scene: Scene, shapes)
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
