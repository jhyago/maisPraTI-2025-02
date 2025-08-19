import styled from "styled-components"

const Container = styled.main `
    min-height: 100vh;
    display: grid;
    place-items: center;
    padding: 20px;
    font-family: Arial, Helvetica, sans-serif;
`

const Card = styled.section `
    width: 360px;
    border: 1px solid blue;
    border-radius: 14px;
    padding: 16px;
`

const Title = styled.h3 `
    margin: 0 0 8px;
    font-size: 20px;
`

const Button = styled.button `
    padding: 10px 14px;
    border-radius: 10px;
    border: 1px solid #111;
    cursor: pointer;

    background: ${({$variant}) => ($variant === 'ghost' ? 'transparent' : '#111')};

    &:hover {
        filter: brightness(0.90);
    }
`

export default function CardStyled() {
    return(
        <Container>
            <Card>
                <Title>Styled-Components</Title>
                <p>Um botão com uma variante</p>

                <div style={{display:"flex", gap: 8}}>
                    <Button>Solid</Button>
                    <Button $variant="ghost">Ghost</Button>
                </div>
            </Card>
        </Container>
    )
}