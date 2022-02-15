const UPDATE_STATE = 'UPDATE_STATE'

const homeInitialState = [
    {
        id: 42,
        title: 'Петли маршрутизации',
        body: 'Как вы, возможно, знаете, **цикл маршрутизации** - это ситуация, когда некоторый пакет маршрутизируется' +
            'бесконечно или почти бесконечно в цикле. И такая ситуация может возникнуть при конвергенции протоколов' +
            'динамической маршрутизации. А для BGP в масштабе Интернета это иногда может занять несколько минут\n' +
            '\n' +
            'Другой *причиной этого может быть застревание* маршрутов в протоколах динамической маршрутизации. Кроме того,' +
            'это может быть вызвано ошибками конфигурации, и такие циклы сохраняются до тех пор, пока ошибка конфигурации' +
            'не будет исправлена.\n' +
            '\n' +
            'И самый простой и, как мне кажется, самый популярный способ попасть в такую ситуацию - использовать' +
            'неиспользуемое IP-пространство или пулы NAT с плохой маршрутизацией.\n' +
            '\n' +
            'Например, если какой-то провайдер назначает ***и направляет некоторое пространство*** своему клиенту, и этот' +
            'клиент использует только часть этого пространства, то оставшиеся адреса направляются обратно провайдеру по' +
            'маршруту по умолчанию. Итак, мы получаем петлю.\n' +
            '\n' +
            '# И эту ситуацию легко\n исправить на стороне клиента, ' +
            'если вы обнуляете все адреса, которые вы получаете от' +
            'провайдера. Любые неиспользуемые адреса удаляются и не зацикливаются.\n' +
            '\n' +
            'Кроме того, если провайдер может реализовать некоторую политику защиты от спуфинга в интерфейсе клиента -' +
            'в этом случае он также может разорвать цикл маршрутизации из-за неправильно настроенного клиента. Так что' +
            'BCP38 здесь полезен.\n',
        status: 'PUBLISHED',
        articleDescription: 'Петли маршрутизации',
        publicationDate: '01:01:2022',
        modificationDate: '01:01:2022',
        authorId: 1,
        nickname: 'Leha-Chel',
        photo: 'https://sun9-32.userapi.com/impf/c830209/v830209268/120d18/lqU5_fb3DKo.jpg?size=1080x810&quality=' +
            '96&sign=af1665c70b3ecfbc34e610daaac4f67e&type=album',
        tags: [
            {
                id: 23,
                name: 'Наука'
            },
            {
                id: 38,
                name: 'Образование'
            }
        ]
    },
    {
        id: 42,
        title: 'Петли маршрутизации',
        body: 'Как вы, возможно, знаете, **цикл маршрутизации** - это ситуация, когда некоторый пакет маршрутизируется' +
            'бесконечно или почти бесконечно в цикле. И такая ситуация может возникнуть при конвергенции протоколов' +
            'динамической маршрутизации. А для BGP в масштабе Интернета это иногда может занять несколько минут\n' +
            '\n' +
            'Другой *причиной этого может быть застревание* маршрутов в протоколах динамической маршрутизации. Кроме того,' +
            'это может быть вызвано ошибками конфигурации, и такие циклы сохраняются до тех пор, пока ошибка конфигурации' +
            'не будет исправлена.\n' +
            '\n' +
            'И самый простой и, как мне кажется, самый популярный способ попасть в такую ситуацию - использовать' +
            'неиспользуемое IP-пространство или пулы NAT с плохой маршрутизацией.\n' +
            '\n' +
            'Например, если какой-то провайдер назначает ***и направляет некоторое пространство*** своему клиенту, и этот' +
            'клиент использует только часть этого пространства, то оставшиеся адреса направляются обратно провайдеру по' +
            'маршруту по умолчанию. Итак, мы получаем петлю.\n' +
            '\n' +
            '# И эту ситуацию легко\n исправить на стороне клиента, ' +
            'если вы обнуляете все адреса, которые вы получаете от' +
            'провайдера. Любые неиспользуемые адреса удаляются и не зацикливаются.\n' +
            '\n' +
            'Кроме того, если провайдер может реализовать некоторую политику защиты от спуфинга в интерфейсе клиента -' +
            'в этом случае он также может разорвать цикл маршрутизации из-за неправильно настроенного клиента. Так что' +
            'BCP38 здесь полезен.\n',
        status: 'PUBLISHED',
        articleDescription: 'Петли маршрутизации',
        publicationDate: '01:01:2022',
        modificationDate: '01:01:2022',
        authorId: 1,
        nickname: 'Leha-Chel',
        photo: 'https://sun9-32.userapi.com/impf/c830209/v830209268/120d18/lqU5_fb3DKo.jpg?size=1080x810&quality=' +
            '96&sign=af1665c70b3ecfbc34e610daaac4f67e&type=album',
        tags: [
            {
                id: 23,
                name: 'Наука'
            },
            {
                id: 38,
                name: 'Образование'
            }
        ]
    }, {
        id: 42,
        title: 'Петли маршрутизации',
        body: 'Как вы, возможно, знаете, **цикл маршрутизации** - это ситуация, когда некоторый пакет маршрутизируется' +
            'бесконечно или почти бесконечно в цикле. И такая ситуация может возникнуть при конвергенции протоколов' +
            'динамической маршрутизации. А для BGP в масштабе Интернета это иногда может занять несколько минут\n' +
            '\n' +
            'Другой *причиной этого может быть застревание* маршрутов в протоколах динамической маршрутизации. Кроме того,' +
            'это может быть вызвано ошибками конфигурации, и такие циклы сохраняются до тех пор, пока ошибка конфигурации' +
            'не будет исправлена.\n' +
            '\n' +
            'И самый простой и, как мне кажется, самый популярный способ попасть в такую ситуацию - использовать' +
            'неиспользуемое IP-пространство или пулы NAT с плохой маршрутизацией.\n' +
            '\n' +
            'Например, если какой-то провайдер назначает ***и направляет некоторое пространство*** своему клиенту, и этот' +
            'клиент использует только часть этого пространства, то оставшиеся адреса направляются обратно провайдеру по' +
            'маршруту по умолчанию. Итак, мы получаем петлю.\n' +
            '\n' +
            '# И эту ситуацию легко\n исправить на стороне клиента, ' +
            'если вы обнуляете все адреса, которые вы получаете от' +
            'провайдера. Любые неиспользуемые адреса удаляются и не зацикливаются.\n' +
            '\n' +
            'Кроме того, если провайдер может реализовать некоторую политику защиты от спуфинга в интерфейсе клиента -' +
            'в этом случае он также может разорвать цикл маршрутизации из-за неправильно настроенного клиента. Так что' +
            'BCP38 здесь полезен.\n',
        status: 'PUBLISHED',
        articleDescription: 'Петли маршрутизации',
        publicationDate: '01:01:2022',
        modificationDate: '01:01:2022',
        authorId: 1,
        nickname: 'Leha-Chel',
        photo: 'https://sun9-32.userapi.com/impf/c830209/v830209268/120d18/lqU5_fb3DKo.jpg?size=1080x810&quality=' +
            '96&sign=af1665c70b3ecfbc34e610daaac4f67e&type=album',
        tags: [
            {
                id: 23,
                name: 'Наука'
            },
            {
                id: 38,
                name: 'Образование'
            }
        ]
    }
]

const homeReducer = (state = homeInitialState, action) => {
    switch (action.type) {
        case UPDATE_STATE: {
            state = action.newState
            return state
        }
        default:
            return state
    }
}

export const updateStateActionCreator = (newState) => ({
    type: UPDATE_STATE,
    newState: newState
})

export default homeReducer
