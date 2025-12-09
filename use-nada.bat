@echo off
REM Switch repo to use account: nadabenali
git config user.name "Nadabenali"
git config user.email "nadabenali619@gmail.com"
git remote set-url origin git@github-nada:wissalbn/kata.git

echo ---- Current Git identity ----
git config user.name
git config user.email
git remote -v
pause
