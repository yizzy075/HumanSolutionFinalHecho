@echo off
echo ========================================
echo Iniciando Frontend Angular
echo ========================================
echo.

cd HumanSolution-Frontend

if not exist "node_modules" (
    echo Instalando dependencias por primera vez...
    call npm install
    if errorlevel 1 (
        echo [ERROR] Error al instalar dependencias
        pause
        exit /b 1
    )
)

echo.
echo Iniciando servidor de desarrollo Angular...
echo El frontend estara disponible en: http://localhost:4200
echo Presiona Ctrl+C para detener el servidor
echo.

call npm start

pause

