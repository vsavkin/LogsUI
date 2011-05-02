<div class="lineCollection">
    <div class="lineCollectionName">
        ${title}
    </div>

    <g:each var="line" status="i" in="${lines}">
        <div class="${i % 2 ? 'evenLine' : 'oddLine'}">${line}</div>
    </g:each>
</div>