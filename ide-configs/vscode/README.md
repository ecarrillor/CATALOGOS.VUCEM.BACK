# Configuración para Visual Studio Code

## 📦 Plugins Requeridos

### Obligatorios:
1. **Checkstyle for Java** - Validación de Checkstyle en tiempo real
2. **Extension Pack for Java** - Soporte completo para Java
3. **Error Lens** - Muestra errores inline en el código

### Opcionales:
1. **GitLens** - Información adicional de Git

## 🚀 Instalación

### 1. Instalar Plugins
```bash
# Abrir VS Code
# Presionar Ctrl+Shift+X
# Buscar e instalar cada plugin listado arriba
```

### 2. Copiar Configuración
```bash
# Copiar el archivo settings.json a tu proyecto:
cp ide-configs/vscode/settings.json .vscode/settings.json
```

### 3. Crear carpeta .vscode (si no existe)
```bash
mkdir .vscode
```

### 4. Reiniciar VS Code
```bash
# Ctrl+Shift+P -> "Developer: Reload Window"
```

## ✅ Verificación

Después de la instalación deberías ver:
- ✅ Errores de Checkstyle en tiempo real
- ✅ Línea vertical a los 120 caracteres
- ✅ Formateo automático al guardar
- ✅ Colores en archivos con errores
- ✅ Panel Problems con lista de errores

## 🔧 Comandos Útiles

- **Ctrl+Shift+M** - Abrir Panel Problems
- **Ctrl+Shift+P** - Command Palette
- **Ctrl+S** - Guardar y formatear automáticamente
- **Ctrl+Shift+F** - Formatear código manualmente

## 🎯 Características Incluidas

- Validación de Checkstyle automática
- Formateo de código al guardar
- Organización de imports automática
- Indicadores visuales de errores
- Configuración de espacios (4 espacios)
- Línea guía de 120 caracteres
- Colores personalizados para errores