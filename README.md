> [!WARNING]
> This project does not accept direct contributions. However, users are free to fork the repository and create alternative versions, provided that proper credit is given to the original source.

---

> [!NOTE]
> This mod was created because [OptiFine 1.21.11 HD U J9](https://optifine.net/adloadx?f=OptiFine_1.21.11_HD_U_J8.jar) does not work properly with Fabric Loader on Minecraft 1.21.11. XRAVCAPES solves this issue by displaying OptiFine capes without requiring OptiFine itself.

---

# XRAVCAPES

[![Build](https://github.com/ravenastar-js/xravcapes/actions/workflows/build.yml/badge.svg)](https://github.com/ravenastar-js/xravcapes/actions/workflows/build.yml) [![License: MIT](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE) [![Minecraft](https://img.shields.io/badge/minecraft-1.21.11-brightgreen.svg)](https://fabricmc.net/use/installer/)

Mod **client-side** para Fabric (Minecraft `1.21.11`) que exibe capas do OptiFine e capas nativas/padrão do Minecraft, sem precisar do OptiFine instalado.

<p align="center">
  <a title="Fabric API" href="https://github.com/FabricMC/fabric">
    <img src="https://i.imgur.com/Ol1Tcf8.png" width="151" height="50" />
  </a>
  <br /><br />
  <a title="Minha Collection no Modrinth" href="https://modrinth.com/collection/QEwOIHfO">
    <img src="https://img.shields.io/badge/Modrinth-Collection-00AF5C?style=for-the-badge&logo=modrinth&logoColor=white" alt="Modrinth Collection" />
  </a>
</p>

## 📋 Requisitos

| Requisito | Versão usada | Link |
|---|---|---|
| Fabric Loader | `>= 0.18.0` | [Instalador oficial](https://fabricmc.net/use/installer/) |
| Fabric API | `0.141.6+1.21.11` | [Modrinth](https://modrinth.com/mod/fabric-api/version/0.141.6+1.21.11) · [GitHub](https://github.com/FabricMC/fabric-api) |
| Minecraft | `1.21.11` | *(nenhum link aplicável)* |
| Java | `>= 21` | [Adoptium](https://adoptium.net/) |

Todos os requisitos acima já são declarados como dependências no `fabric.mod.json`; o Fabric Loader recusa carregar o XRAVCAPES se algum deles estiver ausente ou em versão incompatível.

> 💡 Instale o Minecraft 1.21.11 usando o Fabric Loader.

## ✨ Funcionalidades

- Sistema de **prioridade por pontuação** (hierarquia):
  | Fonte | Score |
  |---|---|
  | Capa do OptiFine | **1.0** |
  | Capa padrão/original do Minecraft | **0.9** |
  | Outras capas (fonte customizada) | **0.8** |
- Menu de configurações acessado inteiramente por comando (sem atalho de teclado), com `/xravcapes` para escolher:
  - **Automático**, que respeita a hierarquia acima. Se o jogador tiver capa do OptiFine e do Minecraft, o mod sempre mostra a do OptiFine.
  - Forçar sempre **OptiFine**.
  - Forçar sempre **Minecraft (vanilla)**.
  - **Desativar** a exibição customizada.
- Pré-visualização rápida da capa resolvida no próprio menu.
- `/xravcapes reload` para forçar a atualização das capas em cache.
- **100% client-side**: não precisa estar no servidor.
- Tradução automática: usa `pt_br` / `pt_pt` se o idioma do Minecraft do jogador for português, e cai para `en_us` (padrão) em qualquer outro idioma. Isso é feito pelo próprio sistema de idiomas do Minecraft, sem código extra, então funciona de forma automática e nativa.

## 🔧 Compatibilidade

Este projeto está **travado especificamente na versão `1.21.11`**:

- `minecraft_version=1.21.11`
- `yarn_mappings=1.21.11+build.6` (última build de Yarn disponível; a partir da próxima versão principal a Mojang passa a exigir Mojang Mappings)
- `loader_version=0.18.1` (mínimo exigido pelo Fabric para 1.21.11)
- `fabric_version=0.141.6+1.21.11`
- Loom `1.14`, exigido a partir do 1.21.11

O `fabric.mod.json` também declara a dependência exata `"minecraft": "1.21.11"`, então o mod recusa carregar em qualquer outra versão, evitando comportamento quebrado/instável em versões não testadas.

## 📜 Créditos

<div align="center">

## Feito com 💚 por [RavenaStar](https://ravenastar.com)

</div>
