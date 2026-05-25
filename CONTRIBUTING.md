# Contributing

Thanks for your interest in improving this course.

## What's welcome

- **Typo / grammar fixes** — open a PR directly
- **Clearer explanations** — open a PR, briefly explain the change
- **Bug fixes in code examples** — open a PR with a test or repro
- **New lesson or major reorganization** — open an issue first to discuss
- **Translations** — open an issue first; we'll set up a `docs/<lang>/` folder

## How to contribute a fix

1. Fork the repo
2. Make your change on a branch
3. Build the site locally to verify:

   ```bash
   pip install -r requirements.txt
   mkdocs serve
   ```

4. Open a PR against `main` with a clear title and description

## Style guide

- **Tone**: friendly, concise, no fluff. Talk to the reader like a colleague.
- **Code examples**: must be runnable as-is, no placeholder `...`
- **One concept per lesson**: don't bundle
- **Show before telling**: code first, explanation second when possible
- **Lint-clean** in whatever language: no `unused`, no commented-out code
- **Markdown headings**: H1 for the lesson title, H2 for sections, H3 sparingly

## Adding a new lesson

1. Create the file under the right `docs/0X-module/NN-lesson-name.md`
2. Add it to the `nav:` block in `mkdocs.yml`
3. Cross-link from prev/next lesson buttons
4. Update the module's `index.md` table

## Reporting issues

Please include:

- Which lesson / page
- What's wrong (broken example? wrong explanation? typo?)
- Your environment if relevant (OS, Flutter / Android Studio version)

## Code of Conduct

Be kind. Assume good faith. The goal is teaching people, not winning arguments.
