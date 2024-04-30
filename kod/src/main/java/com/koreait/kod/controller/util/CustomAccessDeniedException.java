package com.koreait.kod.controller.util;

import java.nio.file.AccessDeniedException;

public class CustomAccessDeniedException extends AccessDeniedException {

    private static final long serialVersionUID = 1L;
    /*
     * serialVersionUID 멤버변수가 없다면
     * CustomAccessDeniedException 클래스가 Serializable 인터페이스를 구현했지만, 해당 클래스에 serialVersionUID를 명시하지 않았기 때문에 경고가 발생함
     * 이 값은 클래스의 구조가 변경될 때 직렬화 된 객체의 버전을 식별하기 위함
     * 클래스를 변경할 때마다 serialVersionUID를 업데이트하여 호환성을 유지하도록 함
     * 
     * 직렬화된 객체의 버전관리란 ?
     * 직렬화된 객체의 버전 관리는 객체를 직렬화하고 다시 역직렬화할 때 클래스의 버전 호환성을 보장하기 위한 것입니다. 클래스를 변경할 때마다 클래스의 구조가 변경되고, 이는 직렬화된 객체와의 호환성 문제를 발생시킬 수 있습니다. 예를 들어, 다음과 같은 상황이 발생할 수 있습니다:
     * 직렬화된 객체를 저장해두었다가 나중에 불러오는 경우
     * 직렬화된 객체를 네트워크를 통해 다른 시스템으로 전송하는 경우
     * 클라이언트와 서버 간 통신 시 객체를 직렬화하여 전송하는 경우
     * 
     * 이러한 경우에 클래스의 구조가 변경되면 문제가 발생할 수 있습니다. 예를 들어, 직렬화된 객체를 저장한 후 클래스를 변경하고 다시 불러오려고 하면 클래스의 버전이 다르기 때문에 역직렬화할 수 없는 상황이 발생할 수 있습니다.
     * 이를 해결하기 위해 Java 직렬화 메커니즘은 직렬화된 객체에 대한 버전 정보를 저장합니다. 이 정보는 serialVersionUID라고 불리며, 클래스의 버전을 식별하는 데 사용됩니다. 만약 직렬화된 객체의 버전 정보와 클래스의 버전 정보가 다르다면 역직렬화 시에 InvalidClassException과 같은 예외가 발생할 수 있습니다.
     * 따라서 클래스를 변경할 때마다 serialVersionUID를 업데이트하여 호환성을 보장하는 것이 중요합니다. 일반적으로는 변경된 클래스의 구조나 필드가 추가되거나 삭제될 때 serialVersionUID를 업데이트해야 합니다. 이를 통해 이전 버전과의 호환성을 유지하면서 객체를 직렬화 및 역직렬화할 수 있습니다.
     * 
     * 직렬화란 ?
     * 직렬화는 객체를 바이트 스트림으로 변환하는 프로세스입니다. 이 과정에서는 객체의 상태와 데이터를 저장하기 위해 필요한 모든 정보가 바이트 스트림으로 변환됩니다. 이렇게 변환된 바이트 스트림은 파일에 저장하거나 네트워크를 통해 전송할 수 있습니다. 주로 데이터의 영속성을 유지하고 객체를 공유하거나 전송하는 데 사용됩니다.
     * 
     * 역직렬화란 ?
     * 역직렬화는 직렬화된 바이트 스트림을 다시 객체로 변환하는 프로세스입니다. 이 과정에서는 저장된 데이터를 읽고, 객체의 상태를 복원하여 메모리에 다시 로드합니다. 역직렬화는 직렬화된 데이터를 읽어와서 원래 객체로 복원하는 과정이므로, 직렬화된 데이터를 영속적으로 저장하거나 네트워크를 통해 전송한 후에 객체를 복원하는 데 사용됩니다.
     * 
     * 직렬화(Serialization)와 역직렬화(Deserialization)는 객체를 바이트 스트림으로 변환하거나, 반대로 바이트 스트림에서 객체로 변환하는 프로세스를 말합니다. 이 프로세스는 객체를 저장하거나 네트워크를 통해 전송할 때 유용하게 사용됩니다.
     * 이러한 직렬화와 역직렬화는 Java에서 기본적으로 제공되는 기능이며, java.io.Serializable 인터페이스를 구현하여 사용할 수 있습니다. 이를 통해 객체를 직렬화하고 역직렬화할 수 있으며, 데이터를 저장하고 전송하는 데 활용할 수 있습니다.
     * 
     * 
     */

    private String role;

    public CustomAccessDeniedException(String message, String role) {
        super(message);
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}