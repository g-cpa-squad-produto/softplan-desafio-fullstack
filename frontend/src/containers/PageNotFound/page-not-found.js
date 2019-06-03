import React from 'react';
import image from '../../assets/img/404.png';

const PageNotFoundContainer = () => (
    <div style={{
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
    }}>
        <img src={image} alt="Página não encontrada" />
    </div>
)

export default PageNotFoundContainer;