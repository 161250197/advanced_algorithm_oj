const fs = require("fs");
const path = require("path");
const contestFolders = fs.readdirSync(__dirname);
contestFolders.forEach(contest => {
    const contestPath = path.join(__dirname, contest);
    if (!/^\d+$/.test(contest))
    {
        return;
    }
    const questionFolders = fs.readdirSync(contestPath);
    questionFolders.forEach(questionFolder => {
        const questionFolderPath = path.join(contestPath, questionFolder);
        if (!/^\d+-\d+$/.test(questionFolder))
        {
            return;
        }
        const files = fs.readdirSync(questionFolderPath);
        const inContents = [];
        const outContents = [];
        files.forEach(file => {
            const content = fs.readFileSync(path.join(questionFolderPath, file));
            if (/^\d\.in$/.test(file))
            {
                inContents.push(content);
            }
            else if (/^\d\.out$/.test(file))
            {
                outContents.push(content);
            }
        });
        if (inContents.length !== outContents.length)
        {
            console.log(`[ERROR] ${ questionFolder }: in out 数量不匹配`);
            return;
        }
        if (!inContents.length)
        {
            console.log(`[WARNING] ${ questionFolder }: 测试用例未生成`);
            return;
        }
        const test = fs.createWriteStream(path.join(questionFolderPath, "test.txt"));
        test.write(inContents.join("\n\n"));
        const answer = fs.createWriteStream(path.join(questionFolderPath, "answer.txt"));
        answer.write(outContents.join("\n\n"));
    });
});
