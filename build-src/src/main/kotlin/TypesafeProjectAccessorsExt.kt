import org.gradle.api.Project

internal val Project.projectsWorkaround: ProjectAccessors
  get() = ProjectAccessors(this)

internal class ProjectAccessors(project: Project) {

  val module = Core(project)

  class Core(private val project: Project) {

    val data get() = project.project(":data")
    val domain get() = project.project(":domain")
    val core get() = project.project(":core")
    val app get() = project.project(":app")
  }
}