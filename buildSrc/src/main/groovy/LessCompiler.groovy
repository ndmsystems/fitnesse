import org.gradle.api.tasks.*
import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection

class LessCompiler extends DefaultTask {
  @Classpath
  final ConfigurableFileCollection classpath = project.objects.fileCollection()

  @InputDirectory
  def inputDir

  @Input
  def mainLessFile

  @OutputFile
  def cssFile

  void classpath(Object... paths) {
    classpath.from(paths)
  }

  @TaskAction
  public void exec() {
    inputDir.mkdirs()
    def taskClasspath = classpath
    cssFile.withOutputStream { output ->
      project.javaexec {
        classpath(taskClasspath)
        mainClass = "org.mozilla.javascript.tools.shell.Main"
        args "extra/lesscss/less-rhino-1.7.0.js", new File(inputDir, mainLessFile)
        standardOutput = output
      }
    }
  }
}
