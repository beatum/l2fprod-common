# L2FProd.com Common Components

:warning: This project is no longer maintained. Not guaranteed to compile with modern JDK.

Swing has lot of components built-in but still some are missing. This
project provides the developer community with these missing
components, components inspired (copied?!) from modern user
interfaces.

<table cellspacing="5" border="0">
  <tr>
    <td align="center">
      <a href="xdocs/ButtonBar.jpg">
        <img src="xdocs/tn-ButtonBar.jpg" width="150" height="112"/>
      </a>
    </td>
    <td align="center">
      <a href="xdocs/PropertySheet.jpg">
        <img src="xdocs/tn-PropertySheet.jpg" width="150" height="112"/>
      </a>
    </td>
  </tr>
  <tr>
    <td align="center">
      <a href="xdocs/FontChooser.jpg">
        <img src="xdocs/tn-FontChooser.jpg" width="150" height="112"/>
      </a>
    </td>
    <td align="center">
      <a href="xdocs/JTaskPane-DirectoryChooser.jpg">
        <img src="xdocs/tn-JTaskPane-DirectoryChooser.jpg" width="150" height="112"/>
      </a>
    </td>
  </tr>
  <tr>
    <td align="center">
      <a href="xdocs/taskpane-ocean.png">
        <img src="xdocs/tn-taskpane-ocean.png"/>
      </a>
    </td>
    <td align="center">
      <a href="xdocs/taskpane-glossy.png">
        <img src="xdocs/tn-taskpane-glossy.png"/>
      </a>
    </td>
  </tr>
</table>
      
## The Components

the JButtonBar - it is a bar made of buttons [sic], you have seen it
in Mozilla Firebird, IntelliJ.

the JOutlookBar - as seen in Outlook, it stacks components together
and allows only one of the stack to be visible at a given time. The
component extends the JTabbedPane, no surprise regarding its API.

the JTaskPane and JTaskPaneGroup - lot of recent applications bring
contextual item lists from which you can pick tasks related to the
current selection or context. The JTaskPane and JTaskPaneGroup deliver
this feature to java applications.

the JFontChooser and JDirectoryChooser - surprisingly Swing has no
font chooser and using the original JFileChooser to select a directory
is kind of not so user friendly...well, you know how it works.
JFontChooser and JDirectoryChooser address these two issues.

the PropertySheet - it puts together a list of properties and their
editors. Each property is given a name, a type, a description.It also
supports JavaBeans through BeanInfos and PropertyDescriptors.

the JTipOfTheDay - it brings the famous "Tip Of The Day" dialog to the
Swing toolkit.

## Build

The project historically used Ant. The current repository contains Mavenized modules under `maven/`.

Quick Maven build (recommended):

```bash
# build the whole multi-module project (skip tests)
mvn -DskipTests=true clean package

# run all tests (may be slow)
mvn clean test
```

Build only the `sheet` module (compile or package):

```bash
# from project root: build the sheet module and its dependencies
mvn -DskipTests=true -pl maven/sheet -am package

# or run from the module dir:
cd maven/sheet
mvn -DskipTests=true package
```

Compile test classes (useful when you added test-only demos):

```bash
mvn -DskipTests=true -pl maven/sheet -am test-compile
```

Run a single module test class (example):

```bash
# run PropertyEditorRegistryUnitTest in the sheet module
mvn -pl maven/sheet -Dtest=PropertyEditorRegistryUnitTest test
```

Run the small PropertySheet demo (the demo is in the test sources and uses test classpath):

```bash
# recommended: from project root so -pl works as expected
mvn -DskipTests=true -pl maven/sheet -am -Dexec.classpathScope=test \
  exec:java -Dexec.mainClass=com.l2fprod.common.demo.PropertySheetDemo

# or from the module folder (do NOT pass -pl here):
cd maven/sheet
mvn -DskipTests=true -Dexec.classpathScope=test \
  exec:java -Dexec.mainClass=com.l2fprod.common.demo.PropertySheetDemo
```

Notes when running the demo and tests
- The demo prefers FlatLaf when available (it tries a few known FlatLaf class names). The repository already includes a test-scoped FlatLaf dependency in `maven/sheet/pom.xml` so running with `-Dexec.classpathScope=test` will pick it up.
- The demo is a Swing application and requires a display. If you are running on a headless CI server, use Xvfb or similar (example):

```bash
# using xvfb-run (Debian/Ubuntu) to run GUI in a virtual X server
xvfb-run mvn -DskipTests=true -pl maven/sheet -am -Dexec.classpathScope=test \
  exec:java -Dexec.mainClass=com.l2fprod.common.demo.PropertySheetDemo
```
- If you get a Maven warning about `nachocalendar` (systemPath pointing at `${project.basedir}/../../lib/nachocalendar.jar`), either place the JAR at that location (legacy layout) or update the module POM to use a repository dependency. This is a legacy system-scope entry and may trigger warnings but does not prevent builds unless the JAR is actually required by your run.

Running the FlatLaf unit test (example):

```bash
mvn -pl maven/shared -Dtest=LookAndFeelAddonsFlatLafTest test
```

If you prefer the demo in `src/main/java` (so it runs without the `-Dexec.classpathScope=test` workaround), I can move it to `maven/sheet/src/main/java` and update the pom accordingly.