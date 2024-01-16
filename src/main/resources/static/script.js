async function addLivro() {
    const tituloInput = document.getElementById('tituloInput');
    
    if (tituloInput.value.trim() !== '') {
        try {
            const response = await fetch('/cadastrarLivro', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    codigo: tituloInput.value
                })
            });

            if (response.ok) {
                console.log('Livro added successfully!');
                tituloInput.value = '';
            } else {
                console.error('Error adding Livro:', response.statusText);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    }
}

async function addAluno() {
    const nomeInput = document.getElementById('nomeInput');
    const LivroInput = document.getElementById('tituloInput');
    
    if (nomeInput.value.trim() !== '') {
        try {
            const randomNum = Math.floor(Math.random() * 10000000) + 1;
            document.getElementById("randomNumber").textContent = randomNum;
            const response = await fetch('/cadastrarAluno', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    nome: nomeInput.value,
                    ra: randomNum.toString()
                })
            });

            if (response.ok) {
                console.log('Aluno added successfully!', nomeInput.value, randomNum);
                nomeInput.value = '';
            } else {
                console.error('Error adding Aluno:', response.statusText);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    }
}

async function addEmprestimo() {
    const nomeInput = document.getElementById('nomeInput');
    const livroInput = document.getElementById('livroInput');
    
    if (nomeInput.value.trim() !== '' && livroInput.value.trim() !== '') {
        try {
            const listItem = document.createElement('li');
            listItem.textContent = `Emprestimo -- RA Aluno: ${nomeInput.value}, Livro: ${livroInput.value}`;
            document.getElementById('taskList').appendChild(listItem);

            const response = await fetch('/realizarEmprestimo', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    nome: nomeInput.value,
                    livro: livroInput.value
                })
            });

            if (response.ok) {
                console.log('Emprestimo feito com Sucesso!', nomeInput.value, livroInput.value);
                nomeInput.value = '';
            } else {
                console.error('Erro ao realizar Emprestimo:', response.statusText);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    }
}

async function addDevolucao() {
    const nomeInput = document.getElementById('nomeInput');
    const livroInput = document.getElementById('livroInput');
    
    if (nomeInput.value.trim() !== '' && livroInput.value.trim() !== '') {
        try {
            const listItem = document.createElement('li');
            listItem.textContent = `Emprestimo -- RA Aluno: ${nomeInput.value}, Livro: ${livroInput.value}`;
            document.getElementById('taskList').appendChild(listItem);

            const response = await fetch('/realizarDevolucao', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    nome: nomeInput.value,
                    livro: livroInput.value
                })
            });

            if (response.ok) {
                console.log('Emprestimo feito com Sucesso!', nomeInput.value, livroInput.value);
                nomeInput.value = '';
            } else {
                console.error('Erro ao realizar Emprestimo:', response.statusText);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    }
}