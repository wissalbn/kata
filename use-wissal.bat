@echo off
REM Switch repo to use account: wissalbn
git config user.name "Wissal Benali"
git config user.email "wissal.benali88@gmail.com"
git remote set-url origin git@github-wissal:wissalbn/kata.git

echo ---- Current Git identity ----
git config user.name
git config user.email
git remote -v
pause
