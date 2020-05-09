@echo off
color 1f
set jdkdir=%java_home%
set home=%~dp0
"%jdkdir%\bin\java" -jar "%home%registry.jar" --spring.config.location=file:%home%registry.dev.yml,file:%home%registry.yml,file:%home%registry.bootstrap.yml