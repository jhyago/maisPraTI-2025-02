import { useParams, Link} from "react-router-dom"

export default function Post() {
    const { id } = useParams()

    return (
        <section>
            <h1>Post #{id}</h1>
            <p>Aqui você está no Post de ID {id}</p>

            <p style={{ marginTop: 12 }}>
                <Link to="posts/100">/posts/100</Link>
            </p>
        </section>
    )
}