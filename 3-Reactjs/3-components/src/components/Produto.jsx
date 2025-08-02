export default function Produto(props) {
    return(
        <>
            <div>
                <h2>{props.name}</h2>
                <p>{props.price}</p>
                <p>{props.description}</p>
            </div>
        </>
    )
}