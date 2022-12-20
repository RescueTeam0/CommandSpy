
# CommandSpy

- Minecraft Spigot/Bukkit plugin made for servers staff, to know what commands are player executing.
- It's possible to ignore specified commands *(e.g. `/login`)*
- Hexadecimal color codes are supported
- Messages are fully customizable in the **`config.yml`** file
## Permissions

| Permission                  | Command                                   | Description                                                                                  |
|:----------------------------|:------------------------------------------|:---------------------------------------------------------------------------------------------|
| **`commandspy.use`**        | `/commandspy ...`                         | **Main permission** to use other subcommands (enable, disable, help)                         |
| `commandspy.see`            | *`Chat messages`*                         | Permission **necessary** to receive CommandSpy messages                                      |
| `commandspy.use.other`      | `/commandspy <enable/disable> <player>`   | Enable or disable CommandSpy to other players                                                |
| `commandspy.bypass`         | *`Bypass`*                                | Messages from players with this permission aren't spied                                      |
| `commandspy.reload`         | `/controllo <player>`                     | Reloads the configuration files                                                              |

## Screenshots

![Help Menù](https://i.imgur.com/LIxuUcp.png)

![Example Message](https://i.imgur.com/dwV3yQQ.png)
## Support

If you need more help, feel free to contact [@Sincrono](https://t.me/Sincrono) on telegram
