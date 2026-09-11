> [!WARNING]
> This project does not accept direct contributions. However, users are free to fork the repository and create alternative versions, provided that proper credit is given to the original source.

---

> [!NOTE]
> This mod was created because [OptiFine 1.21.11 HD U J9](https://optifine.net/adloadx?f=OptiFine_1.21.11_HD_U_J8.jar) does not work properly with Fabric Loader on Minecraft 1.21.11. XRAVCAPES solves this issue by displaying OptiFine capes without requiring OptiFine itself.

---

<p align="center">
  <a href="https://github.com/ravenastar-js/xravcapes">
    <img src="https://raw.githubusercontent.com/ravenastar-js/xravcapes/refs/heads/main/src/main/resources/assets/xravcapes/icon.png" alt="xravcapes" width="150" />
  </a>
</p>

# XRAVCAPES

[![Build](https://github.com/ravenastar-js/xravcapes/actions/workflows/build.yml/badge.svg)](https://github.com/ravenastar-js/xravcapes/actions/workflows/build.yml) [![License: MIT](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE) [![Minecraft](https://img.shields.io/badge/minecraft-1.21.11-brightgreen.svg)](https://fabricmc.net/use/installer/)

A **client-side** mod for Fabric (Minecraft `1.21.11`) that displays OptiFine capes and Minecraft's native/default capes, without needing OptiFine installed.

<p align="center">
  <a title="Fabric API" href="https://github.com/FabricMC/fabric">
    <img src="https://i.imgur.com/Ol1Tcf8.png" width="151" height="50" />
  </a>
  <br /><br />
  <a title="My Collection on Modrinth" href="https://modrinth.com/collection/QEwOIHfO">
    <img src="https://img.shields.io/badge/Modrinth-Collection-00AF5C?style=for-the-badge&logo=modrinth&logoColor=white" alt="Modrinth Collection" />
  </a>
</p>

## ⬇️ Download

**📥 [Direct download — xravcapes-1.0.0.jar](https://github.com/ravenastar-js/xravcapes/releases/download/v1.0.0/xravcapes-1.0.0.jar)**

Looking for older or newer builds? Check the **[Releases page](https://github.com/ravenastar-js/xravcapes/releases)**.

> [!WARNING]
> Do **not** download or place `xravcapes-1.0.0-sources.jar` in your `mods` folder. That file contains only the source code for developers and is **not a functional mod**. Placing it in `mods` will prevent Minecraft from loading the mod correctly and may cause crashes or errors. Use only the `xravcapes-1.0.0.jar` file.

## 📥 Installation

1. Download **only** the file `xravcapes-1.0.0.jar` from the link above.
2. Open your Minecraft mods folder. On Windows, press `Win + R`, paste the path below, and hit **Enter**:
   ```
   %appdata%\.minecraft\mods
   ```
   > 💡 If the `mods` folder doesn't exist yet, create it manually inside `.minecraft`.
3. Place `xravcapes-1.0.0.jar` inside the `mods` folder.
4. Launch Minecraft with **Fabric Loader** (`>= 0.18.0`) and **[Fabric API](https://modrinth.com/mod/fabric-api)** installed.

## 📋 Requirements

| Requirement | Version used | Link |
|---|---|---|
| Fabric Loader | `>= 0.18.0` | [Official installer](https://fabricmc.net/use/installer/) |
| Fabric API | `0.141.6+1.21.11` | [Modrinth](https://modrinth.com/mod/fabric-api/version/0.141.6+1.21.11) · [GitHub](https://github.com/FabricMC/fabric-api) |
| Minecraft | `1.21.11` | *(no applicable link)* |
| Java | `>= 21` | [Adoptium](https://adoptium.net/) |

All requirements above are already declared as dependencies in `fabric.mod.json`; Fabric Loader will refuse to load XRAVCAPES if any of them is missing or has an incompatible version.

> 💡 Install Minecraft 1.21.11 using Fabric Loader.

## ✨ Features

- **Score-based priority system** (hierarchy):

  | Source | Score |
  |---|---|
  | 🧡 OptiFine cape | **1.0** |
  | 💚 Minecraft default/original cape | **0.9** |
  | 🔵 Other capes (custom source) | **0.8** |

- Settings menu accessed entirely through a command (no keybind needed), via `/xravcapes`, to choose:
  - **Automatic**, which follows the hierarchy above. If the player has both an OptiFine and a Minecraft cape, the mod always shows the OptiFine one.
  - Force always **OptiFine**.
  - Force always **Minecraft (vanilla)**.
  - **Disable** custom cape display entirely.
- Quick preview of the resolved cape right in the menu.
- `/xravcapes reload` to force-refresh cached capes.
- **100% client-side**: no need for it to be installed on the server.
- Automatic translation: uses `pt_br` / `pt_pt` if the player's Minecraft language is Portuguese, and falls back to `en_us` (default) for any other language. This is handled natively by Minecraft's own language system, with no extra code, so it works automatically.

## 🔧 Compatibility

This project is **locked specifically to version `1.21.11`**:

- `minecraft_version=1.21.11`
- `yarn_mappings=1.21.11+build.6` (latest available Yarn build; starting from the next major version, Mojang requires Mojang Mappings)
- `loader_version=0.18.1` (minimum required by Fabric for 1.21.11)
- `fabric_version=0.141.6+1.21.11`
- Loom `1.14`, required starting from 1.21.11

`fabric.mod.json` also declares the exact dependency `"minecraft": "1.21.11"`, so the mod refuses to load on any other version, avoiding broken/unstable behavior on untested versions.

## 🔗 Useful links

- **📥 [Direct download — xravcapes-1.0.0.jar](https://github.com/ravenastar-js/xravcapes/releases/download/v1.0.0/xravcapes-1.0.0.jar)**
- **🧵 [Fabric API (Modrinth)](https://modrinth.com/mod/fabric-api)**
- **🚀 [Releases](https://github.com/ravenastar-js/xravcapes/releases)**

## 📜 Credits

<div align="center">

## Made with 💚 by [RavenaStar](https://ravenastar.com)

</div>

---

## 🌟 Star History

<a href="https://www.star-history.com/?repos=ravenastar-js%2Fxravcapes&type=date&legend=top-left">
 <picture>
   <source media="(prefers-color-scheme: dark)" srcset="https://api.star-history.com/chart?repos=ravenastar-js/xravcapes&type=date&theme=dark&legend=top-left" />
   <source media="(prefers-color-scheme: light)" srcset="https://api.star-history.com/chart?repos=ravenastar-js/xravcapes&type=date&legend=top-left" />
   <img alt="Star History Chart" src="https://api.star-history.com/chart?repos=ravenastar-js/xravcapes&type=date&legend=top-left" />
 </picture>
</a>
