## How to contribute to ICU XPath bindings

#### **Did you find a bug?**

* **Ensure the bug was not already reported** by searching on GitHub
  under [Issues](https://github.com/scdh/icu-xpath-bindings/issues).

* If you're unable to find an open issue addressing the problem, [open
  a new
  one](https://github.com/scdh/icu-xpath-bindings/issues/new). Be sure
  to include a **title and clear description**, as much relevant
  information as possible. Include a **code sample** that shows how
  you used the XPath extension. Also include the error message you
  get.

#### **Did you write a patch that fixes a bug?**

* Open a new GitHub pull request with the patch.

* Ensure the PR description clearly describes the problem and
  solution. Include the relevant issue number if applicable.

#### **Did you fix whitespace, format code, or make a purely cosmetic patch?**

Changes that are cosmetic in nature and do not add anything
substantial to the stability, functionality, or testability will
generally not be accepted.

#### **Do you intend to add a new feature or change an existing one?**

* Suggest your change in the developers mentioned in the
  [`pom.xml`](pom.xml) file via e-mail and start writing code.

* Do not open an issue on GitHub until you have positive feedback
  about the change. GitHub issues are primarily intended for bug
  reports and fixes.

#### Release policy

Release numbers follow the `MAJOR.MINOR.BUGFIX` scheme. Releases are
automated by github actions and are triggered by pushing a tag with a
name matching the scheme.

### Supported oXygen Versions

Each major oXygen release depends on a specific Saxon
release. Unfortunately, that means that the plugin has to be build
against such Saxon versions and Oxygen SDK versions. The main
[`pom.xml`](pom.xml) file provides build profiles for Oxygen releases,
that set dependencies. They can be activated by setting the
`oxygen.version` property to a major release, `23`, `24`, `25`. To
support a new oxygen version, a new build profile has to be added and
the `oxygen.versions` property has to be updated, too. E.g. build for
Oxygen 24 run

```{shell}
mvn -Doxygen.version=24 clean package
```

The `oxygen.versions` property has the form

```
MAJOR_VERSION_PLUGIN:SUPPORTED_VERSIONS[|MAJOR_VERSION:SUPPORTED_VERSIONS]*
```

where `SUPPORTED_VERSIONS` is a comma separated list of

```
MAJOR_VERSION.MINOR_VERSION[+]
```

and `MAJOR_VERSION_PLUGIN` is

```
MAJOR_VERSION[.MINOR_VERSION]
```

For each `MAJOR_VERSION_PLUGIN` there has to be a **build profile** and
**release rule** for the [CI/CD
pipeline](.github/workflows/release.yml). So, each supported oxygen
major version has needs an entry in `pom.xml` and in the pipeline file.
