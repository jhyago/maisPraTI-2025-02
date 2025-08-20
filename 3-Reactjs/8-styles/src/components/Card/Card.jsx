import CardStyle from './Card.module.css'

export function Card({title, children}) {
    return (
        <div className={CardStyle.root}>
            <h3 className={CardStyle.title}>{title}</h3>
            <div>{children}</div>
        </div>
    )
}