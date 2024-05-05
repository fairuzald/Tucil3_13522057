
function GridElement({ children }: { children: string }) {
    return (
        <div className='border-2 border-theme-1 font-medium rounded-sm px-3 py-2 flex items-center justify-center text-lg lg:text-xl'>{children}</div>
    )
}

function GridWrapper({ children }: { children: string }) {
    return (
        children && children.length > 0 && (
            <div className="bg-white" style={{ display: "grid", gridTemplateColumns  : `repeat(${children.length}, minmax(0, 1fr))` }}>
                {children.split("").map((elm, i) => (
                    <GridElement key={i}>{elm}</GridElement>
                )
                )}
            </div>
        )
    )
}

function GridSolver({ path }: { path: string[] }) {
    return (
        path && path.length > 0 && (
            <div className="flex flex-col gap-1">
                {path.map((word, i) => (
                    <GridWrapper key={i}>{word}</GridWrapper>
                ))}
            </div>
        )
    )
}

export default GridSolver;
