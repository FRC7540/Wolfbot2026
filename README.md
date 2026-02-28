# MAXSwerve Java Template v2026.0

See [the online changelog](https://github.com/REVrobotics/MAXSwerve-Java-Template/blob/main/CHANGELOG.md) for information about updates to the template that may have been released since you created your project.

## Description

A template project for an FRC swerve drivetrain that uses REV MAXSwerve Modules.

Note that this template is designed for a drivetrain composed of four MAXSwerve Modules, each configured with two SPARKS MAX, a NEO as the driving motor, a NEO 550 as the turning motor, and a REV Through Bore Encoder as the absolute turning encoder. If you are using SPARK Flex for either the drive motor or turning motor, you will need to update the classes accordingly.

To get started, make sure you have calibrated the zero offsets for the absolute encoders in Hardware Client 2 using the `Absolute Encoder` utility under the associated turning SPARK devices.

## Prerequisites

* SPARK MAX Firmware v26.1.0
* REVLib v2026.0.0

## Configuration

It is possible that this project will not work for your robot right out of the box. Various things like the CAN IDs, PIDF gains, chassis configuration, etc. must be determined for your own robot!

These values can be adjusted in the `Configs.java` and `Constants.java` files.

## Shane's change log

### NavX Configuration

In VSCode, use the Command Palette to install the Studica vendor dep.

Procedure:

1. Open the command palette (Ctrl + Shift + P)
2. Execute the "WPILib: Manage Vendor Libraries" command
3. Select "Install new libraries (online)"
4. Input this url: `https://dev.studica.com/maven/release/2026/json/Studica-2026.0.0.json`
    - retrieved from: https://github.com/Studica-Robotics/NavX/tree/main
5. When prompted to rebuild, accept.

Expected Results:

- `vendor/deps/Studica.json` will be created.
- NavX libraries will resolve in the code.

Notes:

- The GitHub page suggests another means of installing the vendor deps that I
  just glossed over.