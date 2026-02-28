# Wolfbot 2026

FRC Team 7540's 2026 code based on the MaxSwerve Template

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