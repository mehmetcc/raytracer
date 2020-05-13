package math

import render.Color

/**
 * DAVID DAVENPORT'UN ASKERLERİYİZ
 *
 * @author Mehmet Can Altuntaş
 * github.com/mehmetcc
 */



trait Shape {
  def center: Point
  def radius: Double
  def color: Color
  // def record: List[HitEntry]

  // mmphh, function polymorphism
  def hit[s <: Shape](ray: Ray): Option[Color]
}

case class Sphere(center: Point, radius: Double, color: Color) extends Shape {

  def hit[Sphere](ray: Ray): Option[Color] = if (discriminant(ray) > 0.0) Some(illuminate(ray)) else None

  /** TODO return the point of intersection inside Option[Point] */
  private def discriminant(ray: Ray): Double = {
    val distance = ray.origin - center

    // we are doing discriminant analysis here
    val a = ray.direction dot ray.direction
    val b = (distance dot ray.direction) * 2.0
    val c = (distance dot distance) - Math.pow(radius, 2)
    val discriminant = Math.pow(b, 2) - 4*a*c

    if (discriminant < 0) -1 else (-b - Math.sqrt(discriminant)) / (2.0 * a)
  }

  private def illuminate(ray: Ray): Color = {
    val t = discriminant(ray)
    val N = (ray.pointAt(t) - center).unit

    (Vector3D(N.x + 1, N.y + 1, N.z + 1) ** 0.5).toColor
  }
}


