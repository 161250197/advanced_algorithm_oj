; (function () {
    const card = document.getElementsByClassName('ivu-card')[0];

    const questionId = /problem\/(\d+\-\d+)/.exec(window.location.pathname)[1];

    const questionName = `# ${ questionId } ${ card.getElementsByClassName('ivu-card-head')[0].innerText }`;

    function trim (str) {
        return str.replace(/^\s+/, '').replace(/\s+$/, '');
    }

    const titleAndContent = [
        ...card.querySelectorAll('.markdown-body > .title, .markdown-body > .content')
    ]
        .map(e => {
            const text = trim(e.innerText);
            const isTitle = [...e.classList].includes('title');
            if (isTitle)
            {
                return `## ${ text }`;
            }
            return text;
        });

    const sample = card.getElementsByClassName('sample')[0];

    let sampleArr = [];

    if (sample !== undefined)
    {
        sampleArr.push('## Sample');

        const inputAndOutput = [
            ...sample.querySelectorAll('.sample-input > .title, .sample-input > pre, .sample-output > .title, .sample-output > pre')
        ]
            .map(e => {
                const text = trim(e.innerText);
                const isTitle = [...e.classList].includes('title');
                if (isTitle)
                {
                    return `### ${ text }`;
                } else
                {
                    return ['~~~', text, '~~~'].join('\n');
                }
            });

        sampleArr.push(...inputAndOutput);
    }

    const content = [
        questionName,
        ...titleAndContent,
        ...sampleArr
    ]
        .join('\n\n');

    function copyToClipboard (str) {
        const textarea = document.createElement('textarea');
        document.body.appendChild(textarea);
        textarea.value = str;
        textarea.select();
        document.execCommand('copy');
        document.body.removeChild(textarea);
    }

    copyToClipboard(content);

    console.log(content);
    console.log('[INFO] 上述内容已复制到剪切板');
}());
