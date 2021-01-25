/**
 * 获取命令行参数
 * @returns {Array<String>}
 */
function getArgs () {
    return process.argv.slice(2);
}

/**
 * 检查目标路径是否为目录
 * @param {fs.PathLike} path 
 * @returns {boolean}
 */
function isFolder (path) {
    if (!fs.existsSync(path))
    {
        return false;
    }
    const target = fs.statSync(path);
    return target.isDirectory();
}

const [contestId, questionId] = getArgs();

console.log(`[INFO] contestId: ${ contestId } questionId: ${ questionId }`);

const path = require('path');
const fs = require('fs');


const contestFolderPath = path.join(__dirname, contestId);

if (!isFolder(contestFolderPath))
{
    fs.mkdirSync(contestFolderPath);
}

const questionFolderPath = path.join(contestFolderPath, questionId);

if (!isFolder(questionFolderPath))
{
    fs.mkdirSync(questionFolderPath);
}

const templateMainFilePath = path.join(__dirname, 'template', 'Main.java');
const targetMainFilePath = path.join(questionFolderPath, 'Main.java');

if (fs.existsSync(targetMainFilePath))
{
    console.log(`[INFO] 代码文件已存在: ${ targetMainFilePath }`);
} else
{
    fs.copyFileSync(templateMainFilePath, targetMainFilePath);
    console.log(`[INFO] 已拷贝代码模板: ${ targetMainFilePath }`);
}

const readmeFilePath = path.join(questionFolderPath, 'README.md');

if (fs.existsSync(readmeFilePath))
{
    console.log(`[INFO] README 文件已存在: ${ readmeFilePath }`);
} else
{
    fs.createWriteStream(readmeFilePath);
    console.log(`[INFO] README 文件已生成: ${ readmeFilePath }`);
}
